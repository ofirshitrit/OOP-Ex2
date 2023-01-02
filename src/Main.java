import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        String[] names = Ex2_1.createTextFiles(5,3,9);
        System.out.println(Arrays.toString(names));

        int rows = Ex2_1.getNumOfLines(names);
        System.out.println("Num Of Rows: " + rows);

        int rows1 = Ex2_1.getNumOfLinesThreads(names);
        System.out.println("Num Of Rows: " + rows1);
//
        int rows2 = Ex2_1.getNumOfLinesThreadPool(names);
        System.out.println("Num Of Rows: " + rows2);


    }
}