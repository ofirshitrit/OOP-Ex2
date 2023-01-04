import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class Ex2_1 {
    public static String[] createTextFiles(int n,int seed,int bound){
        String[] namesOfFiles = new String[n];
        try {
            for (int i = 0; i < n; i++) {
                String nameFile = "files\\file_" + (i+1);
                namesOfFiles[i] = nameFile;
                FileWriter myWriter = new FileWriter(nameFile);
                Random rand = new Random(2); // TODO - make help function
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

    private static int computeNumLines(String fileName) throws FileNotFoundException {
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
            totalNumOfLines += computeNumLines(fileName);
        }
        return totalNumOfLines;
    }

    public static int getNumOfLinesThreads(String[] fileNames) throws InterruptedException {
        NumOfLinesThread[] threads = new NumOfLinesThread[fileNames.length];
        int totalNumOfLines = 0;

        for (int i = 0; i < threads.length ; i++) {
            threads[i] = new NumOfLinesThread(fileNames[i]);
        }
        for(NumOfLinesThread thread : threads){
            thread.start();
        }

        for (NumOfLinesThread thread : threads) // TODO ASK ROY ON ERROR
        {
            thread.join();
        }
        for (NumOfLinesThread thread : threads)
        {
            totalNumOfLines += thread.numOfRows;
        }
        return totalNumOfLines;
    }

    public static int getNumOfLinesThreadPool(String[] fileNames) throws ExecutionException, InterruptedException {
        int totalNumOfLines = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(fileNames.length);
        List<Future<Integer>> numOfLines = new ArrayList<>();
        for (String fileName : fileNames){
            Callable<Integer> lines = new NumOfLinesCallable(fileName);
            numOfLines.add(executorService.submit(lines));
        }
        for (Future<Integer> lines : numOfLines){
            totalNumOfLines += lines.get();
        }
        executorService.shutdown();
        return totalNumOfLines;
    }

}

    



//}


