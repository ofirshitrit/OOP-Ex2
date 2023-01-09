package Ex2_2;

public class Main {

    public static void main(String[] args)
    {
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

        Task<Boolean> t4 = Task.createTask(()->{
            return true;
        }, TaskType.OTHER);


        CustomExecutor executor = new CustomExecutor();

//        System.out.println(executor.getPq().poll());
//        System.out.println(executor.getPq().poll());
//        System.out.println(executor.getPq().poll());
//        System.out.println(executor.getPq().poll());

       // System.out.println(t1.compareTo(t3));







    }

    }

