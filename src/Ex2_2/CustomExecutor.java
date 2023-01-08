package Ex2_2;


import java.util.concurrent.*;

public class CustomExecutor {
   // private ExecutorService executor = Executors.newFixedThreadPool(4);
    PriorityBlockingQueue<Runnable> pq = new PriorityBlockingQueue<>();
    ThreadPoolExecutor pool;
    int poolSize = 1;
    int MaxPoolSize = 3;
    public CustomExecutor(){
        pool = new ThreadPoolExecutor(poolSize,MaxPoolSize,300L ,TimeUnit.MILLISECONDS,pq){
            @Override
            protected <V> RunnableFuture<V> newTaskFor(Callable<V> c) {
                return new PriorityFutureTaskWrapper<>((Task<V>) c);
            }
        };
    }
    public <V> Future<V> submit(Task<V> task) {
        return pool.submit(task);
    }

    public <V> Future<V> submit(Callable<V> callable, TaskType type) {
        return pool.submit(callable);
    }

    public Integer getCurrentMax() {
        return 0;
    }

    public void gracefullyTerminate() {

    }
}