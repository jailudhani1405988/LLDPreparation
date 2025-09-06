/**
 * The Retry Queue that manages jobs and worker threads.
 */

/**
 * Example usage
 */
public class RetryQueueDemo {
	public static void main(String[] args) {
		RetryQueue retryQueue = new RetryQueue(2);

		// Example job: fails first 2 times, succeeds on 3rd attempt
		Job unreliableJob = new Job() {
			int counter = 0;

			@Override
			public void execute() throws Exception {
				counter++;
				if (counter < 3) {
					throw new RuntimeException("Simulated failure");
				}
				System.out.println("Job logic executed successfully!");
			}
		};

		RetryJob retryJob = new RetryJob(unreliableJob, 5, 0); // max 5 retries, no initial delay
		retryQueue.add(retryJob);

		// Keep main thread alive to see retries
		try {
			Thread.sleep(20000);
		} catch (InterruptedException ignored) {
		}
		retryQueue.shutdown();
	}
}
