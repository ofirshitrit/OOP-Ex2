package Ex2_2;

import java.lang.reflect.Type;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {

    public static void main(String[] args)
    {
//        Task<Integer> task = new Task<>();
////        int res = task.Test((Integer x) -> x+1, 5);
//////        System.out.println(res); //6
        Task<Integer> t1 = Task.createTask(()->{
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);

        Task<String> t2 = Task.createTask(()-> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            return sb.reverse().toString();
        }, TaskType.IO);

        Task<Integer> t3 = Task.createTask(()->{
            int sum = 1;
            for (int i = 1; i <= 10; i++) {
                sum *= i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);


//        PriorityFutureTaskWrapper<Integer> compute = new PriorityFutureTaskWrapper<>(t1);
//        PriorityFutureTaskWrapper<String> pq1 = new PriorityFutureTaskWrapper<>(t2);
//        PriorityFutureTaskWrapper<Integer> compute2 = new PriorityFutureTaskWrapper<>(t3);

//        PriorityQueue<PriorityFutureTaskWrapper<?>> queue = new PriorityQueue<>(); // the priority queue
//        queue.add(pq1);
//        queue.add(compute);
//        queue.add(compute2);

        CustomExecutor executor = new CustomExecutor();
        executor.submit(t1);
        executor.submit(t2);
        executor.submit(t3);
        executor.shutDown();

        System.out.println(executor.maxPriority);

//
//        for (PriorityFutureTaskWrapper<?> wrapper : queue) {
//            Task<?> task = wrapper.getPriorityTask();
//            System.out.println(task); // print the task
//        }

    }

    }

