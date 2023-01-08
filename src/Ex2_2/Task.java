package Ex2_2;

import java.util.concurrent.Callable;

public class Task<V> implements Callable<V>{
    Callable<V> callable;
    TaskType type;

    private Task(Callable<V> callable, TaskType type) {
        this.callable =  callable;
        this.type = type;
    }

    public static <V> Task<V> createTask(Callable<V> callable, TaskType type)
    {
        return new Task<>(callable, type);
    }
    public static <V> Task<V> createTask(Callable<V> callable)
    {
        TaskType defaulteType = TaskType.OTHER;
        return new Task<>(callable, defaulteType);
    }

    @Override
    public V call() throws Exception {
        return callable.call();
    }

    Callable<V> getCallable() {
        return this.callable;
    }


    @Override
    public String toString() { //TODO CHANGE IT
        return "The type is " + type;

    }
}
