package Ex2_2;

import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    public static void main(String[] args)
    {

        PriorityBlockingQueue<Integer> q = new PriorityBlockingQueue<>();
        q.add(20);
        q.add(10);
        q.add(1);
        q.add(0);
        q.add(300);

        System.out.println(q);
//        Task<Integer> task = new Task<>();
//        int res = task.Test((Integer x) -> x+1, 5);
//        System.out.println(res); //6
    }

    }
