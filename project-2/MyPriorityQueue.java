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
        while (!maxHeap.isEmpty()) {
            Task current = dequeue();
            current.incrementWaitingTime();

            if (current.getWaitingTime() >= timeToIncrementPriority) {
                current.resetWaitingTime();

                if (current.getPriority() < maxPriority) {
                    current.setPriority(current.getPriority() + 1);
                    maxHeap.insert(current);
                }
            }
        }
    }
}
