import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class MaxHeap<T extends Comparable<T>> {
    private static final int DEFAULT_SIZE = 10;
    private T[] entries;
    private int n;

    public MaxHeap() {
        this.entries = (T[]) new Comparable[DEFAULT_SIZE];
        this.n = 0;
    }

    public MaxHeap(Collection<? extends T> entries) {
        this.entries = (T[]) new Comparable[entries.size()];
        for (T entry : entries) {
            insert(entry);
        }
    }

    public static void main(String[] args) {
        // int a = (8 >> 1) - 1;
        ArrayList<Integer> test = new ArrayList<>();
        test.add(2);
        test.add(1);
        test.add(3);
        test.add(5);
        test.add(4);
        test.add(6);
        test.add(7);
        test.add(8);
        test.add(9);

        MaxHeap<Integer> heap = new MaxHeap<>(test);
        heap.buildMaxHeap();
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int left(int index) {
        return (index * 2) + 1;
    }

    private int right(int index) {
        return (index * 2) + 2;
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

    public T extractMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T max = max();
        entries[0] = entries[size() - 1];
        n--;
        heapify(0);
        return max;
    }

    public T max() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return entries[0];
    }

    public void insert(T element) {
        if (entries.length <= size()) {
            resize(size() * 2);
        }

        if (isEmpty()) {
            entries[0] = element;
            n++;
            return;
        }

        n++;
        entries[n - 1] = element;
        int i = n - 1;
        while (i >= 0 && entries[i].compareTo(entries[parent(i)]) > 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public void increaseKey(int index, T value) throws HeapException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

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

    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        return entries[index];
    }

    private void resize(int size) {
        T[] resized = Arrays.copyOf(entries, size);
        entries = resized;
    }

    private void swap(int a, int b) {
        T temp = entries[a];
        entries[a] = entries[b];
        entries[b] = temp;
    }
}