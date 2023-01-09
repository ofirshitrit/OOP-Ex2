package Ex2_2;


import java.util.concurrent.*;

public class CustomExecutor {
    private PriorityBlockingQueue<Runnable> pq = new PriorityBlockingQueue<>();
    private ThreadPoolExecutor pool;

    int maxPriority = Integer.MAX_VALUE;

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
        if (maxPriority > task.getType().getPriorityValue()){
            maxPriority = task.getType().getPriorityValue();
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

    public Integer getCurrentMax() {
        return maxPriority;
    }

    public void gracefullyTerminate() {
        pool.shutdown();
    }

    public PriorityBlockingQueue<Runnable> getPq() {
        return pq;
    }

    public void setPq(PriorityBlockingQueue<Runnable> pq) {
        this.pq = pq;
    }

    public ThreadPoolExecutor getPool() {
        return pool;
    }

    public void setPool(ThreadPoolExecutor pool) {
        this.pool = pool;
    }

    public int getMaxPriority() {
        return maxPriority;
    }

    public void setMaxPriority(int maxPriority) {
        this.maxPriority = maxPriority;
    }
}