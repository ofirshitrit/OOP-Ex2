package Ex2_2;


import java.util.concurrent.*;

public class CustomExecutor {
   // private ExecutorService executor = Executors.newFixedThreadPool(4);
    PriorityBlockingQueue<Runnable> pq = new PriorityBlockingQueue<>();
    ThreadPoolExecutor pool;

    int maxPriority = Integer.MIN_VALUE;

    public CustomExecutor(){
        int coreSize = (Runtime.getRuntime().availableProcessors())/2;
        int maxPoolSize = (Runtime.getRuntime().availableProcessors())-1;
        pool = new ThreadPoolExecutor(coreSize,maxPoolSize,300L ,TimeUnit.MILLISECONDS,pq){
            @Override
            protected <V> RunnableFuture<V> newTaskFor(Callable<V> c) {
                return new PriorityFutureTaskWrapper<>((Task<V>) c);
            }
        };
    }
    public <V> Future<V> submit(Task<V> task) {
        if (maxPriority < task.type.getPriorityValue()){
            maxPriority = task.type.getPriorityValue();
        }
        return pool.submit(task);
    }

    public <V> Future<V> submit(Callable<V> callable)  {
       //return pool.submit(new Task<>(callable,TaskType.COMPUTATIONAL));
        return submit(Task.createTask(callable));
    }

    public <V> Future<V> submit(Callable<V> callable, TaskType type) {
       // return pool.submit(new Task<>(callable,type));
        return submit(Task.createTask(callable,type));
    }

//    public int comparePriority(Task<V> task1 , Task<V> task1)

    public Integer getCurrentMax() {

        return maxPriority;
    }

    public void gracefullyTerminate() {

    }
}