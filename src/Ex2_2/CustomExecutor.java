package Ex2_2;

import java.lang.reflect.Type;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CustomExecutor extends ThreadPoolExecutor {

    private PriorityBlockingQueue<Integer> priorityQueue = new PriorityBlockingQueue<>();
    private static final int numOfCores = Runtime.getRuntime().availableProcessors();
    private static final int corePoolSize = numOfCores/2;
    private static final int maxPoolSize = numOfCores-1;
    public CustomExecutor(){
        super(corePoolSize,maxPoolSize,300L ,TimeUnit.MILLISECONDS,new PriorityBlockingQueue<>());

    }
    public void gracefullyTerminate() {
    }

//    public String getCurrentMax() {
//    }


    public Task submit(Task task){
        return null; //add to queue
    }
    public Task submit(Supplier<Type> supplier , TaskType taskType) {
        return null;
    }
    public Task submit(Supplier<Type> supplier){
        return null;
    }
}