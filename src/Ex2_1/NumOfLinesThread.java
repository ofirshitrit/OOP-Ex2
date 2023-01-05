package Ex2_1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NumOfLinesThread extends Thread {
    String fileName;
    int numOfRows = 0;

    public NumOfLinesThread(String fileName){

        this.fileName = fileName;
    }

    private int computeNumLines(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        int numOfLines = 0;
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            sc.nextLine();
            numOfLines++;
        }
        return numOfLines;
    }

    @Override
    public void run() {
        try {
           numOfRows += computeNumLines(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
