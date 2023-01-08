package Ex2_2;

import java.lang.reflect.Type;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class CustomExecutor {

    private PriorityBlockingQueue<Task> priorityQueue;

    private static final int numOfCores = Runtime.getRuntime().availableProcessors();
    private ThreadPoolExecutor pool;
    private static final int corePoolSize = numOfCores/2;
    private static final int maxPoolSize = numOfCores-1;

    private int MaxPriority = Integer.MIN_VALUE;
    public CustomExecutor(){
        this.pool = new ThreadPoolExecutor(corePoolSize,maxPoolSize,300L ,TimeUnit.MILLISECONDS,new PriorityBlockingQueue<>());
        priorityQueue = new PriorityBlockingQueue<>(); //TODO - not the same queue beacuse it need to be runnable
    }
    public void gracefullyTerminate()
    { //11

    }

    public int getCurrentMax() {
        return MaxPriority;
    }


    public Future submit(Task task){
        return null; // TODO

//        int currPriority = task.getTaskType().getPriorityValue();
//        priorityQueue.add(currPriority);
//        if (MaxPriority < currPriority){
//            MaxPriority = currPriority;
//        }
    }
    public Task submit(Callable<?> callable , TaskType taskType)
    { //TODO
        return null;
    }
    public Task submit(Callable<?> callable)
    { //TODO
        return null;
    }

}