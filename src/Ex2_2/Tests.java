package Ex2_2;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

public class Tests {
    public static final Logger logger = Logger.getLogger(Tests.class.getName());
    @Test
    public void test1() throws Exception{
    /*
     1. task is running
     */
        CustomExecutor customExecutor = new CustomExecutor();
        var task = Task.createTask(()->{
            int sum = 0;
            for (int i = 1; i <= 4; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);
        var sumTask = customExecutor.submit(task);
        final int sum = sumTask.get();

        Assertions.assertEquals(10, sum);
    }

    @Test
    public void test2() throws Exception{
    /*
     2. task is running according to priority order (asc)
     */

        CustomExecutor customExecutor = new CustomExecutor();
        var firstTask = Task.createTask(()->{
            Thread.sleep(1000);
            return 0;
        }, TaskType.COMPUTATIONAL);

        var cTask = Task.createTask(()->{
            System.out.println("cTask");
            return System.currentTimeMillis();
        }, TaskType.COMPUTATIONAL);
        var iTask = Task.createTask(()->{
            System.out.println("iTask");
            return 0;
        }, TaskType.IO);
        var oTask = Task.createTask(()->{
            System.out.println("oTask");
            return 0;
        }, TaskType.OTHER);

        customExecutor.submit(firstTask);
        for(int i=0;i<50;i++) {
            customExecutor.submit(iTask);
            customExecutor.submit(oTask);
            customExecutor.submit(cTask);
        }
        System.out.println("Done");
//        Thread.sleep(2000);
        customExecutor.gracefullyTerminate();
    }

    @Test
    public void test3() throws Exception{
    /*
     3. check getCurrentMax()
     */
        CustomExecutor customExecutor = new CustomExecutor();
        var task1 = Task.createTask(()->{
            System.out.println("task1 is done");
            return 1;
        }, TaskType.COMPUTATIONAL);
        var task11 = Task.createTask(()->{
            System.out.println("task11 is done");
            return 2;
        }, TaskType.COMPUTATIONAL);
        var task2 = Task.createTask(()->{
            System.out.println("task2 is done");
            return 3;
        }, TaskType.IO);
        var task22 = Task.createTask(()->{
            System.out.println("task22 is done");
            return 3;
        }, TaskType.IO);
        var task3 = Task.createTask(()->{
            System.out.println("task3 is done");
            return 1;
        }, TaskType.OTHER);
        var task33 = Task.createTask(()->{
            System.out.println("task33 is done");
            return 1;
        }, TaskType.OTHER);

        customExecutor.submit(task3);
        System.out.println("MAX: " + customExecutor.getCurrentMax());
        customExecutor.submit(task33);
        System.out.println("MAX: " + customExecutor.getCurrentMax());
        customExecutor.submit(task1);
        System.out.println("MAX: " + customExecutor.getCurrentMax());
        customExecutor.submit(task2);
        System.out.println("MAX: " + customExecutor.getCurrentMax());
        customExecutor.submit(task11);
        System.out.println("MAX: " + customExecutor.getCurrentMax());
        customExecutor.submit(task22);
        System.out.println("MAX: " + customExecutor.getCurrentMax());
    }
    @Test
    public void partialTest() throws InterruptedException {
        CustomExecutor customExecutor = new CustomExecutor();
        var task = Task.createTask(()->{
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);
        var sumTask = customExecutor.submit(task);
        final int sum;
        try {
            sum = sumTask.get(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        logger.info(()-> "Sum of 1 through 10 = " + sum);
        Callable<Double> callable1 = ()-> {
            return 1000 * Math.pow(1.02, 5);
        };
        Callable<String> callable2 = ()-> {
            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            return sb.reverse().toString();
        };

        var priceTask = customExecutor.submit(()-> {
            return 1000 * Math.pow(1.02, 5);
        }, TaskType.COMPUTATIONAL);
        var reverseTask = customExecutor.submit(callable2, TaskType.IO);
        final Double totalPrice;
        final String reversed;
        try {
            totalPrice = priceTask.get();
            reversed = reverseTask.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        logger.info(()-> "Reversed String = " + reversed);
        logger.info(()->String.valueOf("Total Price = " + totalPrice));
        logger.info(()-> "Current maximum priority = " +
        customExecutor.getCurrentMax());
        customExecutor.gracefullyTerminate();
    }
}
