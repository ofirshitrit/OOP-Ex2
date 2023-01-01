import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class Ex2_1 {
    public static String[] createTextFiles(int n,int seed,int bound){
        String[] namesOfFiles = new String[n];
        try {
            for (int i = 0; i < n; i++) {
                String nameFile = "file_" + (i+1);
                namesOfFiles[i] = nameFile;
                FileWriter myWriter = new FileWriter(nameFile);
                Random rand = new Random(seed); // TODO - make help funtion
                int numOfRows = rand.nextInt(bound);
                for (int j = 1; j <= numOfRows ; j++) {
                    myWriter.write("Hello World!\n"); // TODO - what to write?
                }
                myWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return namesOfFiles;
    }



    private static int numLines(String path) throws FileNotFoundException {
        File file = new File(path);
        int lines = 0;
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            sc.nextLine();
            lines++;
        }
        return lines;
    }


    public static int getNumOfLines(String[] fileNames) throws IOException {
        int totalNumOfRows = 0;

        for(String fileName : fileNames){
           totalNumOfRows += numLines(fileName);
        }
        return totalNumOfRows;
    }

    public int getNumOfLinesThreads(String[] fileNames){
        int totalNumOfRows = 0;
        for(String fileName : fileNames){
            NumOfLinesThread numOfLinesThread = new NumOfLinesThread(fileName);
        }

        return totalNumOfRows;

    }
}


