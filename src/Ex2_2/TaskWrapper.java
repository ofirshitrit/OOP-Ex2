package Ex2_2;

import java.util.concurrent.FutureTask;

class TaskWrapper<V> extends FutureTask<V> implements Comparable<TaskWrapper<V>> {
    Task<V> _task;

    public TaskWrapper(Task<V> priorityTask) {
        super(priorityTask);
        _task = priorityTask;
    }

    public Task<V> getTask() {
        return _task;
    }


    @Override
    public String toString() {
        return "Task: " + _task;
    }

    @Override
    public int compareTo(TaskWrapper<V> other) {
        int difference = _task.getType().getPriorityValue() - other._task.getType().getPriorityValue();
        if (difference == 0) {
            if (_task < other) { //if task is add to the queue before other  //TODO
                return difference;
            } else {
                return other._task.getType().getPriorityValue() - _task.getType().getPriorityValue();
            }
        }
        return difference;
    }
}