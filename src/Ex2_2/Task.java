package Ex2_2;

import java.util.concurrent.Callable;

public class Task<V> implements Callable<V> , Comparable<Task<V>>{
    private Callable<V> callable;
    private TaskType type;

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
    public Callable<V> getCallable() {
        return this.callable;
    }

    public void setCallable(Callable<V> callable) {
        this.callable = callable;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "The type is " + type + " and it's priority is: " + type.getPriorityValue();
    }
    @Override //TODO
    public int compareTo(Task<V> o) {
        return this.type.ordinal()-o.type.ordinal();
    }
}
