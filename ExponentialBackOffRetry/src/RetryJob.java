import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

 /**
 * A wrapper that keeps retry count and calculates backoff delay.
 */
public class RetryJob implements Delayed {
    private final Job job;
    private final int maxRetries;
    private final AtomicInteger attempts = new AtomicInteger(0);

    private long startTime; // when this job becomes ready

    public RetryJob(Job job, int maxRetries, long initialDelayMillis) {
        this.maxRetries = maxRetries;
        this.startTime = System.currentTimeMillis() + initialDelayMillis;
        this.job = job;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        return Long.compare(this.startTime, ((RetryJob) other).startTime);
    }

    /**
     * Try running the job. If it fails and retries are left, reschedule with backoff.
     */
    public void run(RetryQueue retryQueue) {
        try {
            job.execute();
            System.out.println("✅ Job succeeded on attempt " + (attempts.get() + 1));
        } catch (Exception e) {
            int attempt = attempts.incrementAndGet();
            if (attempt <= maxRetries) {
                long backoff = (long) Math.pow(2, attempt) * 1000; // exponential backoff
                this.startTime = System.currentTimeMillis() + backoff;
                System.out.println("⚠️ Job failed on attempt " + attempt +
                        ". Retrying in " + backoff + " ms");
                retryQueue.add(this);
            } else {
                System.out.println("❌ Job failed after max retries.");
            }
        }
    }
}
