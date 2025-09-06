import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RetryQueue {
    private final DelayQueue<RetryJob> queue = new DelayQueue<>();
    private final ExecutorService workerPool;

    public RetryQueue(int workerThreads) {
        this.workerPool = Executors.newFixedThreadPool(workerThreads);
        start();
    }

    public void add(RetryJob job) {
        queue.put(job);
    }

    private void start() {
        Thread dispatcher = new Thread(() -> {
            while (true) {
                try {
                    RetryJob job = queue.take(); // waits until a job is ready
                    workerPool.submit(() -> job.run(this));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        dispatcher.setDaemon(true);
        dispatcher.start();
    }

    public void shutdown() {
        workerPool.shutdown();
    }
}
