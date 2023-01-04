# OOP-Ex2
## Part 1
### THREADS
![image](https://user-images.githubusercontent.com/117759983/210529690-c68698b9-b270-477c-ad8d-1382fb43807b.png)

In this part we created several text files and we calculated the total number of lines in these files.
We implemented this in three methods:
+  without threads
+ with threads
+ with ThreadPool

### createTextFiles
We write the createTextFiles method that created n file text and a rqndom number of lines.
This function return the names files' array.

### getNumOfLines
 We write the getNumOfLines method that get list of files and return the number  of total rows in all files. In this function we go over the files and calculated for each file the total number of lines.

### getNumOfLinesThreads
We write the getNumOfLinesThreads method that get list of files and return the total number of rows in all files. We created NumOfLinesThread class that extends Thread class.
In the run function we call the computeNumLines function. 

### getNumOfLinesThreadPool
We write the getNumOfLinesThreadPool method that get list of files and return the total number of rows in all files.
We created NumOfLinesThread class that implementes Callable<Integer> interface.
In the call function we call the computeNumLines function.

#### Running Time
We check the run time of 1,000 files with number or rows between 3-1,000.
![image](https://user-images.githubusercontent.com/117759983/210539096-3fcf3ffb-b87e-4eaf-912f-9c9d291bfa85.png)
We can see that the thredPool function is the fastier and then the thread function and in the end the function without threads.
