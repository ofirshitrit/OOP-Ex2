package Ex2_2;

import java.lang.reflect.Type;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task<T> implements Callable<T> {

    private TaskType taskType;
    private int priority;
       T result;
    //private TaskFunction<T> function;
    private Callable<T> function;

    public Task(Callable<T> function)
    {
        this.priority = 5; // TODO defaulter priority
        this.function = function;
    }

    public Task(Callable<T> function,TaskType taskType)
    {
        this.priority = taskType.getPriorityValue();
        this.taskType = taskType;
        this.function = function;

    }
//    public T Test(TaskFunction<T> function , T val) {
//        this.function = function;
//        return function.genericFunction(val);
//    }

    public static Task<?> createTask(Callable<?> function, TaskType taskType) {
        if (taskType.getPriorityValue() == 1) {
            return new Task<>(function,taskType);
        } else if (taskType.getPriorityValue() == 2) {
            return new Task<>(function,taskType);
        } else if (taskType.getPriorityValue() == 3) {
            return new Task<>(function,taskType);
        } else {
            throw new IllegalArgumentException("Invalid type");
        }
    }

    @Override
    public T call() throws Exception { //TODO
        return null;
    }

    public  <T> T get()
    {//TODO - try catch
            return result;

    }


}
