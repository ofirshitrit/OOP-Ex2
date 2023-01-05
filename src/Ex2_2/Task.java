package Ex2_2;

import java.lang.reflect.Type;
import java.net.Proxy;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Task<T> implements Callable<T> {

    private TaskType taskType;
    private TaskFunction<T> function;
    

    public Task()
    {

    }
    public T Test(TaskFunction<T> function , T val) {
        this.function = function;
        return function.genericFunction(val);
    }

//    public static Task<?> createTask(Supplier<Type> supplier, TaskType taskType) {
//        if (taskType.getPriorityValue() == 1) {
//            return new Task<>(taskType);
//        } else if (taskType.getPriorityValue() == 2) {
//            return new Task<>(taskType);
//        } else if (taskType.getPriorityValue() == 3) {
//            return new Task<>(taskType);
//        } else {
//            throw new IllegalArgumentException("Invalid type");
//        }
//    }
    public Type makeTask(){
        return null;
    }
    

    public TaskType getTaskType() {
        return taskType;
    }



    @Override
    public T call() throws Exception {
        return null;
    }
}
