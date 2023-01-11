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
![image](https://user-images.githubusercontent.com/117759983/211515017-4d58ef88-b0a5-4617-81bb-f9914459e24b.png)
 
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

3.defult TaskType

4.submitted time

The submitted time feild exsists for save the time that the task was submitted.

#### The methods class are:

To create an instance of Task we use factory method, it is why the constructor is private. There are two functions of createTask:
* The first get Callable and TaskType and return a new object of Task.
* The second get Callable and return a new object of Task. In this function we define the defult TaskType to be the lowest priority- we chose this beacuse if someone give us task without priority it probability means that the task is not importent to him. 

### TaskWrapper
TaskWrapper exstends from FutureTask<V> and implements Comparable<TaskWrapper<V>>.
This class wrapper the task to be FutureTask type.
The method compareTo compares between two tasks by thier priority and by the time that they was submitted.
### CustomExecutor
This class is an Executor that asynchronously computes Task instance. 
#### The feilds class are:
1.PriorityBlockingQueue

2.TheradPoolExecutor

3.maxPriority 

#### The methods class are:
* constructor:
This code creates a custom thread pool executor using the ThreadPoolExecutor class. The core pool size is set to half the number of available processors and the maximum pool size is set to one less than the number of available processors. The keep-alive time for idle threads is set to 300 milliseconds. The thread pool uses a priority queue (pq) as its work queue.

The newTaskFor method is overridden to return a new task wrapper, which wraps a Task object of type V. The beforeExecute method is overridden to set the priority of the thread according to the priority of the task. The afterExecute method is overridden to decrement the maxPriority array counter for the task.

The toThreadPriority method is used to map the runnable's priority value to a thread priority. It maps runnable's priority to thread priority by subtracting it from maximum priority Thread.MAX_PRIORITY+1.

The maxPriority Array is used to keep track of number of runnable in thread pool with same priority.

We have 3 function of submit:
* The first, get Task and return future. In the implemention of the function we call the TheradPoolExecutor to do submit with the task that we get.
* The second, get Callable and return the first submit function. We send to the first submit the createTask function that gets Callable.
*  The last, get Callable and TaskType and return the first submit function. We send to the first submit the createTask function that gets Callable and TaskType.

* getCurrentMax function return the max priority of the queue.

* gracefullyTerminate function needs to:
 1. Do not allow additional tasks to be queued.
 2. Perform all remaining tasks in the queue
 3. Finish all tasks currently in progress in the CustomExecutor threads collection.


 


