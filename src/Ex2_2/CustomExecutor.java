package Ex2_2;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomExecutor {
    private PriorityBlockingQueue<Runnable> pq = new PriorityBlockingQueue<>();
    private ThreadPoolExecutor pool;

    private AtomicBoolean isDuringShutdown = new AtomicBoolean(false);

    private int maxPriority = Integer.MAX_VALUE;//TODO

    public CustomExecutor() {
        int coreSize = (Runtime.getRuntime().availableProcessors()) / 2;
        int maxPoolSize = (Runtime.getRuntime().availableProcessors()) - 1;
        pool = new ThreadPoolExecutor(coreSize, maxPoolSize, 300L, TimeUnit.MILLISECONDS, pq) {
            @Override
            protected <V> RunnableFuture<V> newTaskFor(Callable<V> callable) {
                // need to return runnable that is also comparable
//                System.out.println(callable.getClass());
                return new TaskWrapper<>((Task<V>) callable);
            }
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                int runnablePriority = ((TaskWrapper<?>)r).getTask().getType().getPriorityValue();
                t.setPriority(toThreadPriority(runnablePriority));
            }
            private int toThreadPriority(int runnablePriority) {
                return Thread.MAX_PRIORITY + 1 - runnablePriority;
            }

        };
    }

    public <V> Future<V> submit(Task<V> task) {
        if (maxPriority > task.getType().getPriorityValue()) {
            maxPriority = task.getType().getPriorityValue();
        }
        if(isDuringShutdown.get()) throw new RuntimeException("");

        return pool.submit(task);
    }

    public <V> Future<V> submit(Callable<V> callable) {
        //return pool.submit(new Task<>(callable,TaskType.COMPUTATIONAL));
        return submit(Task.createTask(callable));
    }

    public <V> Future<V> submit(Callable<V> callable, TaskType type) {
        // return pool.submit(new Task<>(callable,type));
        return submit(Task.createTask(callable, type));
    }

    public Integer getCurrentMax() { //TODO
        return ((TaskWrapper<?>)pq.peek()).getTask().getType().getPriorityValue();
//        return maxPriority;
    }

    public void gracefullyTerminate() {
        // 1. No new tasks can be submitted into Queue
        isDuringShutdown.set(true);
        // 2. Wait until all tasks in Queue are done
        //submit task with lowest priority -> shutdown the pool
        //

        pool.shutdown();


    }


}