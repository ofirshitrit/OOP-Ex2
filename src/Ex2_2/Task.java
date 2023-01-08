package Ex2_2;

import java.util.concurrent.Callable;

public class Task<V>  implements Callable<V>{
    Callable<V> callable;
    TaskType type;

    public Task(Callable<V> callable, TaskType type) {

        this.callable =  callable;
        this.type = type;
    }

    public static <V> Task<V> createTask(Callable<V> callable, TaskType type) {
        return new Task<>(callable, type);
    }

    @Override
    public V call() throws Exception {
        return callable.call();
    }

    Callable<V> getCallable() {
        return this.callable;
    }

}
