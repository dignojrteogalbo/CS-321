import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

// @SuppressWarnings("unchecked")
public class MaxHeap<T extends Comparable<T>> {
    private static final int DEFAULT_SIZE = 10;
    private Comparable[] entries;
    private int n;

    public <T extends Comparable<T>> MaxHeap() {
        this.entries = new Comparable[DEFAULT_SIZE];
        this.n = 0;
    }

    public <T extends Comparable<T>> MaxHeap(Collection<? extends T> entries) {
        this.entries = new Comparable[entries.size()];
        for (T entry : entries) {
            insert(entry);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(4);
        test.add(3);
        test.add(2);
        test.add(5);

        MaxHeap<Integer> heap = new MaxHeap<>(test);
    }

    private int parent(int index) {
        return index >> 1;
    }

    private int left(int index) {
        return index << 1;
    }

    private int right(int index) {
        return index << 1 | 1;
    }

    public void heapify(int index) {
        int left = left(index);
        int right = right(index);

        int largest;

        if (left < size() && entries[left].compareTo(entries[index]) > 0) {
            largest = left;
        } else {
            largest = index;
        }

        if (right < size() && entries[right].compareTo(entries[largest]) > 0) {
            largest = right;
        }

        if (largest != index) {
            swap(index, largest);
            heapify(largest);
        }
    }

    public T extractMax() throws HeapException {
        T max = max();
        entries[0] = entries[size() - 1];
        n--;
        heapify(0);
        return max;
    }

    private T max() throws HeapException {
        if (size() < 1) {
            throw new HeapException("Heap Underflow");
        }

        return (T) entries[0];
    }

    public <T extends Comparable<T>> void insert(T element) {
        if (entries.length <= size()) {
            resize(size() * 2);
        }

        if (isEmpty()) {
            entries[0] = element;
            n++;
            return;
        }

        n++;
        entries[n-1] = entries[n-2];

        try {
            increaseKey(n-1, element);
        } catch (HeapException err) {
            System.out.println(err.getMessage());
        }
    }

    public <T extends Comparable<T>> void increaseKey(int index, T value) throws HeapException {
        if (entries[index].compareTo(value) > 0) {
            throw new HeapException("New value must be larger than current value");
        }

        entries[index] = value;
        int i = index;
        while (i >= 0 && entries[i].compareTo(entries[parent(i)]) > 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void buildMaxHeap() {
        for (int i = (size() - 1) / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    public int size() {
        return n;
    }

    private void resize(int size) {
        Comparable[] resized = Arrays.copyOf(entries, size);
        entries = resized;
    }

    private void swap(int a, int b) { 
        Comparable temp = entries[a];
        entries[a] = entries[b];
        entries[b] = temp;
    }
}
