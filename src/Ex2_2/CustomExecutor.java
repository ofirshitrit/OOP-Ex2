package Ex2_2;


import java.util.concurrent.*;

public class CustomExecutor {
   // private ExecutorService executor = Executors.newFixedThreadPool(4);
    PriorityBlockingQueue<Runnable> pq = new PriorityBlockingQueue<>();
    ThreadPoolExecutor pool;
    int coreSize = (Runtime.getRuntime().availableProcessors())/2;
    int maxPoolSize = (Runtime.getRuntime().availableProcessors())-1;
    public CustomExecutor(){
        pool = new ThreadPoolExecutor(coreSize,maxPoolSize,300L ,TimeUnit.MILLISECONDS,pq){
            @Override
            protected <V> RunnableFuture<V> newTaskFor(Callable<V> c) {
                return new PriorityFutureTaskWrapper<>((Task<V>) c);
            }
        };
    }
    public <V> Future<V> submit(Task<V> task) {
        return pool.submit(task);
    }

    public <V> Future<V> submit(Callable<V> callable)  {
       //return pool.submit(new Task<>(callable,TaskType.COMPUTATIONAL));
       return submit(new Task<>(callable,TaskType.COMPUTATIONAL));
    }

    public <V> Future<V> submit(Callable<V> callable, TaskType type) {
        //return pool.submit(new Task<>(callable,type));
        return submit(new Task<>(callable,type));
    }


    public Integer getCurrentMax() {
        return 0;
    }

    public void gracefullyTerminate() {

    }
}