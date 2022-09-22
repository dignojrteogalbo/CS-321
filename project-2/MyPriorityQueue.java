public class MyPriorityQueue<T extends Comparable<T>> extends MaxHeap<T> implements PriorityQueueInterface {
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
        // for (int index = 0; index < maxHeap.size(); index++) {
        //     maxHeap.increaseKey(index, maxHeap.get(index) + 1);
        // }
        
    }
    
}
