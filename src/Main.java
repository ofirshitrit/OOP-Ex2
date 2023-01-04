import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        String[] names = Ex2_1.createTextFiles(1000,3,1000);
        System.out.println(Arrays.toString(names));
        //1
        long start1 = System.currentTimeMillis();
        int rows1 = Ex2_1.getNumOfLines(names);
        System.out.println("Num Of Rows: " + rows1);
        long end1 = System.currentTimeMillis();
        System.out.println("function1 takes " + (end1 - start1)*0.001+"Seconds");

        //2
        long start2 = System.currentTimeMillis();
        int rows2 = Ex2_1.getNumOfLinesThreads(names);
        System.out.println("Num Of Rows: " + rows2);
        long end2 = System.currentTimeMillis();
        System.out.println("function2 takes " + (end2 - start2)*0.001+"Seconds");

        //3
        long start3 = System.currentTimeMillis();
        int rows3 = Ex2_1.getNumOfLinesThreadPool(names);
        System.out.println("Num Of Rows: " + rows3);
        long end3 = System.currentTimeMillis();
        System.out.println("function3 takes " + (end3 - start3)*0.001+"Seconds");










    }
}