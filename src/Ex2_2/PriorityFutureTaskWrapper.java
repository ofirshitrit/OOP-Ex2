package Ex2_2;

import java.util.concurrent.FutureTask;

class PriorityFutureTaskWrapper<V> extends FutureTask<V>
        {
    Task<V> _priorityTask;

    public PriorityFutureTaskWrapper (Task<V> priorityTask) {
        super(priorityTask);
        _priorityTask = priorityTask;
    }

    public Task<V> getPriorityTask () {
        return _priorityTask;
    }


    @Override
    public String toString() {
        return "Task: " +  _priorityTask;
    }
}