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

            if (current.getWaitingTime() >= timeToIncrementPriority && maxPriority < priority) {
                Task increment = new Task(priority + 1, current.getTaskType(), current.getWaitingTime(), current.getHourCreated(), current.getTaskDescription());

                try {
                    maxHeap.increaseKey(index, increment);
                } catch (HeapException err) {
                    System.out.println("HEAP EXCEPTION");
                }
            }
        }
    }
}
