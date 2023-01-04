import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class NumOfLinesCallable implements Callable<Integer>{
    String fileName;

    public NumOfLinesCallable(String fileName){
        this.fileName = fileName;
    }

    private static int computeNumLines(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        int numOfLines = 0;
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            sc.nextLine();
            numOfLines++;
        }
        return numOfLines;
    }

    @Override
    public Integer call() throws Exception {
        int totalNumOfRows = 0;
        totalNumOfRows += computeNumLines(fileName);
        return totalNumOfRows;
    }
}
