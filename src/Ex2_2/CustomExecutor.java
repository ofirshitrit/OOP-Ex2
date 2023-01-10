package Ex2_2;


import org.junit.jupiter.params.shadow.com.univocity.parsers.tsv.TsvFormat;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomExecutor {
    private PriorityBlockingQueue<Runnable> pq = new PriorityBlockingQueue<>();
    private ThreadPoolExecutor pool;

    private AtomicBoolean isDuringShutdown = new AtomicBoolean(false);

    public int[] maxPriority = new int[10];//TODO - private

    public CustomExecutor() {
        int coreSize = (Runtime.getRuntime().availableProcessors()) / 2;
        int maxPoolSize = (Runtime.getRuntime().availableProcessors()) - 1;
        pool = new ThreadPoolExecutor(2, 5, 300L, TimeUnit.MILLISECONDS, pq) {  //TODO
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
                maxPriority[runnablePriority-1]--;
            }
            private int toThreadPriority(int runnablePriority) {
                return Thread.MAX_PRIORITY + 1 - runnablePriority;
            }

        };
    }

    public <V> Future<V> submit(Task<V> task) {
        task.setSubmittedTime(System.currentTimeMillis());
        maxPriority[task.getType().getPriorityValue()-1]++;
        if(isDuringShutdown.get()) throw new RuntimeException("");

        return pool.submit(task);
    }

    public <V> Future<V> submit(Callable<V> callable) {
        return submit(Task.createTask(callable));
    }

    public <V> Future<V> submit(Callable<V> callable, TaskType type) {
        return submit(Task.createTask(callable, type));
    }

    public Integer getCurrentMax() { //TODO
        //return ((TaskWrapper<?>)pq.peek()).getTask().getType().getPriorityValue();
        for (int i = 0; i < maxPriority.length; i++) {
            if(maxPriority[i] != 0){
                return i+1;
            }
        }
        return 0;
    }

    public void gracefullyTerminate() {
        // 1. No new tasks can be submitted into Queue
        isDuringShutdown.set(true);
        // 2. Wait until all tasks in Queue are done
        //submit task with lowest priority -> shutdown the pool
        Task<Integer> task = Task.createTask(()-> {
            System.out.println("last task");
            pool.shutdown();
            return 0;},TaskType.OTHER);
        submit(task);
    }


}