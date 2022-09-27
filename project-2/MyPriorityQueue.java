public class MyPriorityQueue extends MaxHeap<Task> implements PriorityQueueInterface {
    MaxHeap<Task> maxHeap;

    public MyPriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    @Override
    public void enqueue(Object task) {
        maxHeap.insert((Task) task);
    }

    @Override
    public Task dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public void update(int timeToIncrementPriority, int maxPriority) {
        for (int index = 0; index < maxHeap.size(); index++) {
            Task current = maxHeap.get(index);
            int priority = current.getPriority();
            TaskInterface.TaskType taskType = current.getTaskType();
            int hourCreated = current.getHourCreated();
            String description = current.getTaskDescription();

            if (current.getWaitingTime() >= timeToIncrementPriority) {
                current.resetWaitingTime();
                if (maxPriority < priority) {
                    Task value = new Task(priority + 1, taskType, 0, hourCreated, description);

                    try {
                        maxHeap.increaseKey(index, value);
                    } catch (HeapException err) {}
                }
            }
        }
    }
    
}
