import java.util.ArrayList;

public class MyPriorityQueue extends MaxHeap<Task> implements PriorityQueueInterface {
    @Override
    public void enqueue(Object task) {
        insert((Task) task);
    }

    @Override
    public Task dequeue() {
        return extractMax();
    }

    @Override
    public void update(int timeToIncrementPriority, int maxPriority) {
        ArrayList<Task> stack = new ArrayList<>();

        while (!isEmpty()) {
            Task current = dequeue();
            current.incrementWaitingTime();

            if (current.getWaitingTime() >= timeToIncrementPriority) {
                current.resetWaitingTime();

                if (current.getPriority() < maxPriority) {
                    current.setPriority(current.getPriority() + 1);
                }
            }

            stack.add(current);
        }

        for (Task task : stack) {
            insert(task);
        }
    }
}
