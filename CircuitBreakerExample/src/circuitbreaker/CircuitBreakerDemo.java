package circuitbreaker;

import java.time.Duration;
import java.util.function.Supplier;

public class CircuitBreakerDemo {
    public static void main(String[] args) throws InterruptedException {
        CircuitBreaker breaker = new CircuitBreaker(3, Duration.ofSeconds(5));

        // Fake API that fails first 4 times, then succeeds
        Supplier<String> unreliableApi = new Supplier<String>() {
            int counter = 0;
            public String get() {
                counter++;
                if (counter < 5) throw new RuntimeException("API failure!");
                return "API success!";
            }
        };

        for (int i = 0; i < 10; i++) {
            try {
                String result = breaker.call(unreliableApi);
                System.out.println("Call result: " + result);
            } catch (Exception e) {
                System.out.println("Call blocked/failure: " + e.getMessage());
            }
            Thread.sleep(1000);
        }
    }
}
