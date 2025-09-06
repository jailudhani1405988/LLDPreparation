package circuitbreaker;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

class CircuitBreaker {
    private final int failureThreshold;
    private final Duration openTimeout;
    private int failureCount = 0;
    private CircuitState state = CircuitState.CLOSED;
    private Instant lastFailureTime;

    public CircuitBreaker(int failureThreshold, Duration openTimeout) {
        this.failureThreshold = failureThreshold;
        this.openTimeout = openTimeout;
    }

    public <T> T call(Supplier<T> apiCall) throws Exception {
        switch (state) {
            case OPEN:
                if (Instant.now().isAfter(lastFailureTime.plus(openTimeout))) {
                    // move to HALF_OPEN
                    state = CircuitState.HALF_OPEN;
                    System.out.println("⏳ Circuit breaker moving to HALF_OPEN");
                } else {
                    throw new RuntimeException("🚫 Circuit is OPEN. Call blocked.");
                }
                break;
            case HALF_OPEN:
                // allow single trial call
                return tryCall(apiCall, true);
        }
        // default: CLOSED
        return tryCall(apiCall, false);
    }

    private <T> T tryCall(Supplier<T> apiCall, boolean isHalfOpen) throws Exception {
        try {
            T result = apiCall.get();
            reset();
            if (isHalfOpen) {
                System.out.println("✅ Trial call succeeded, closing circuit.");
            }
            return result;
        } catch (Exception e) {
            recordFailure();
            if (isHalfOpen) {
                trip();
            }
            throw e;
        }
    }

    private void reset() {
        failureCount = 0;
        state = CircuitState.CLOSED;
    }

    private void recordFailure() {
        failureCount++;
        lastFailureTime = Instant.now();
        if (failureCount >= failureThreshold) {
            trip();
        }
    }

    private void trip() {
        state = CircuitState.OPEN;
        System.out.println("⚡ Circuit breaker OPENED due to failures.");
    }
}
