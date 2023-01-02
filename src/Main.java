import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] names = Ex2_1.createTextFiles(2,3,6);
        System.out.println(Arrays.toString(names));
        int rows = Ex2_1.getNumOfLinesThreads(names);
        System.out.println("num of rows: " + rows);
    }
}