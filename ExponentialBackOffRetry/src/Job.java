
/**
 * Represents a unit of work that can be retried.
 */
public interface Job {
    void execute() throws Exception; // Task logic
}
