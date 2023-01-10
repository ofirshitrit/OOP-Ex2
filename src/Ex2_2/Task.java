package Ex2_2;

import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task<V> implements Callable<V> {
    private static final TaskType DEFAULT_TASK_TYPE = TaskType.OTHER;
    private Callable<V> callable;
    private TaskType type;
    private long submittedTime;

    private Task(Callable<V> callable, TaskType type) {
        this.callable = callable;
        this.type = type;
    }

    public static <V> Task<V> createTask(Callable<V> callable, TaskType type) {
        return new Task<>(callable, type);
    }

    public static <V> Task<V> createTask(Callable<V> callable) {
        return createTask(callable, DEFAULT_TASK_TYPE);
    }

    @Override
    public V call() throws Exception {
        return callable.call();
    }

    public Callable<V> getCallable() {
        return this.callable;
    }

    public TaskType getType() {
        return type;
    }

    public long getSubmittedTime(){
        return submittedTime;
    }

    public void setSubmittedTime(long submittedTime) {
        this.submittedTime = submittedTime;
    }

    @Override
    public String toString() {
        return "The type is " + type + " and it's priority is: " + type.getPriorityValue();
    }

}
