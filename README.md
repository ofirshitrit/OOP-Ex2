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

## Part 2
![image](https://user-images.githubusercontent.com/117759983/211291038-1bba7acc-35d2-4227-b5f5-0fefa3143c5e.png)
 
In a multi-threaded environment, sometimes we need to schedule tasks based on custom criteria instead of just the creation time.
In this part we will show how we can achieve this in Java – using a PriorityBlockingQueue.

### TaskType
Is a enum that represent the task type and it priority 
### Task
The Task class represent a generic task with a task type and a priority.
This class implements Callable interface. 
#### The feilds class are:
1.Callable 

2.TaskType

To create an instance of Task we use factory method, it is why the constructor is private. There are two functions of createTask:
* The first get Callable and TaskType and return a new object of Task.
* The second get Callable and return a new object of Task. In this function we define the defult TaskType to be the lowest priority- we chose this beacuse if someone give us task without priority it probability means that the task is not importent to him. 

### PriorityFutureTaskWrapper
PriorityFutureTaskWrapper exstends from FutureTask<V>.
This class wrapper the task to be FutureTask type.
### CustomExecutor
This class is an Executor that asynchronously computes Task instance. 
#### The feilds class are:
1.PriorityBlockingQueue

2.TheradPoolExecutor

3.maxPriority 

#### The methods class are:
constructor****************************

We have 3 function of submit:
* The first, get Task and return future. In the implemention of the function we call the TheradPoolExecutor to do submit with the task that we get.
* The second, get Callable and return the first submit function. We send to the first submit the createTask function that gets Callable.
*  The last, get Callable and TaskType and return the first submit function. We send to the first submit the createTask function that gets Callable and TaskType.

getCurrentMax function return the max priority of the queue.
gracefullyTerminate function needs to shutDown the TheradPoolExecutor.
 


