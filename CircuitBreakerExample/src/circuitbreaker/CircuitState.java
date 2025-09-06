package circuitbreaker;
import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

enum CircuitState {
    CLOSED, OPEN, HALF_OPEN
}
