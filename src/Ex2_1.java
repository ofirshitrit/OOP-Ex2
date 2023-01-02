import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class Ex2_1 {
    public static String[] createTextFiles(int n,int seed,int bound){
        String[] namesOfFiles = new String[n];
        try {
            for (int i = 0; i < n; i++) {
                String nameFile = "file_" + (i+1);
                namesOfFiles[i] = nameFile;
                FileWriter myWriter = new FileWriter(nameFile);
                Random rand = new Random(seed); // TODO - make help function
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

    private static int comuteNumLines(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        int numOfLines = 0;
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            sc.nextLine();
            numOfLines++;
        }
        return numOfLines;
    }

    public static int getNumOfLines(String[] fileNames) throws IOException {
        int totalNumOfLines = 0;

        for(String fileName : fileNames){
            totalNumOfLines += comuteNumLines(fileName);
        }
        return totalNumOfLines;
    }

    public static int getNumOfLinesThreads(String[] fileNames){
        int totalNumOfLines = 0;
        for(String fileName : fileNames){
            NumOfLinesThread numOfLinesThread = new NumOfLinesThread(fileName);
            numOfLinesThread.run();
            totalNumOfLines += numOfLinesThread.numOfRows;
        }

        return totalNumOfLines;

    }
    
    public static int getNumOfLinesThreadPool(String[] fileNames) throws ExecutionException, InterruptedException {
        int totalNumOfLines = 0;

        for (String fileName : fileNames){
            Callable<Integer> task = new Task(fileName);
            ExecutorService executorService = Executors.newFixedThreadPool(fileNames.length);
            Future<Integer> numOfLines = executorService.submit(task);
            totalNumOfLines += numOfLines.get();
            executorService.shutdown();
        }
        return totalNumOfLines;
    }

}

    



//}


