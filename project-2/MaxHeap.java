import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * A generic MaxHeap with an array implementation.
 *
 * @author Digno JR Teogalbo
 * @version CS321 Fall 2022
 */
@SuppressWarnings("unchecked")
public class MaxHeap<T extends Comparable<T>> {
    private static final int DEFAULT_SIZE = 10;
    private T[] entries; // array for items in the MaxHeap
    private int n;       // number of items in the MaxHeap

    /**
     * Creates a MaxHeap with an initial array size of 10 (the default size) and a
     */
    public MaxHeap() {
        this.entries = (T[]) new Comparable[DEFAULT_SIZE];
        this.n = 0;
    }


    /**
     * Creates a MaxHeap from a provided collection. Sets the array size and size of
     * the MaxHeap to the number of elements in the collection.
     * 
     * @param entries Collection of comparable objects to initialize the MaxHeap
     */
    public MaxHeap(Collection<? extends T> entries) {
        this.entries = (T[]) new Comparable[entries.size()];
        for (T entry : entries) {
            insert(entry);
        }
    }


    /**
     * Retrieves the index of the parent from the given index. Left children are
     * caluclated as index * 2 and right children are calculated as index * 2 + 1.
     * Because of this we can calculate the parent index from the children index as
     * index / 2.
     * 
     * @param index index of the current element
     * @return int index of the parent of the element at the given index
     */
    private int parent(int index) {
        // subtract 1 because we are starting heap at index 0
        // 2 - 1 / 2 will result in 1 when we expect 0
        return (index - 1) / 2;
    }

    
    /**
     * Retrieves the index of the left child from the given index. Left child
     * indexes are calculated from index * 2.
     * 
     * @param index index of the current element
     * @return int index of the left child of the element at the given index
     */
    private int left(int index) {
        // add one because we are starting heap at index 0
        // 2 * 0 will result in 0 when we expect 1
        return (index * 2) + 1;
    }

    
    /**
     * Retrieves the index of the right child from the given index. Right child
     * indexes are calculated from index * 2 + 1.
     * 
     * @param index index of the current element
     * @return int index of the right child of the element at the given index
     */
    private int right(int index) {
        // add additional one because we are starting heap at index 0
        // 2 * 0 + 1 will result in 1 when we expect 2
        return (index * 2) + 2;
    }

    
    /**
     * Recursively heapifies the MaxHeap downwards starting from the element at the
     * given index. Proper heaps will have a parent that is greater than both left
     * and right children.
     * 
     * @param index index of the heap to heapify
     */
    public void heapify(int index) {
        int left = left(index);
        int right = right(index);

        int largest = index;

        if (left < size() && entries[left].compareTo(entries[index]) > 0) {
            largest = left;
        }

        if (right < size() && entries[right].compareTo(entries[largest]) > 0) {
            largest = right;
        }

        // heapify downwards
        if (largest != index) {
            swap(index, largest);
            heapify(largest);
        }
    }

    
    /**
     * Removes the root element of the MaxHeap and decrements the size of the
     * MaxHeap by one. The parent element has a greater value and its children,
     * therefore the first item in the array or the root node will be the maximum of
     * the MaxHeap. If the MaxHeap is empty, this method will throw a
     * NoSuchElementException.
     * 
     * @return T max element of the MaxHeap
     */
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

    
    /**
     * Retrieves the root element of the MaxHeap but does not remove it. If the
     * MaxHeap is empty, this method will throw a NoSuchElementException.
     * 
     * @return T max element of the MaxHeap
     */
    public T max() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return entries[0];
    }

    
    /**
     * Inserts an element into the MaxHeap and bubbles the element up the heap,
     * until it is at a position that satisfies the MaxHeap property.
     * 
     * @param element element to insert in the MaxHeap
     */
    public void insert(T element) {
        // resize if there is no space
        if (entries.length <= size()) {
            resize(size() * 2);
        }

        // the first element to be inserted
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

    
    /**
     * Increases the value of an element at a given index in the MaxHeap. If the
     * MaxHeap is empty, this method will throw a NoSuchElementException. If the
     * boolean (index < 0 || index >= size()) is true, this method will throw an
     * IndexOutOfBoundsException.
     * 
     * @param index index of the key/element in the MaxHeap
     * @param value value to increase the key/element by
     * @throws HeapException if the value is less than the value of the element at
     *                       the given index
     */
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

    
    /** 
     * Returns the boolean value for if the MaxHeap is empty.
     * 
     * @return boolean true if the number of elements in the MaxHeap is 0 otherwise false
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Organizes the heap so that it satisfies the MaxHeap properties, that is, each
     * node in the heap has a value less than or equal to its parent.
     */
    public void buildMaxHeap() {
        // heapify from the leaves
        for (int i = (size() - 1) / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    
    /** 
     * Returns the number of elements in the MaxHeap
     * 
     * @return int number of elements in the MaxHeap
     */
    public int size() {
        return n;
    }


    /**
     * Returns the element at the given index in the MaxHeap. If the MaxHeap is
     * empty, this method will throw a NoSuchElementException. If the boolean (index
     * < 0 || index >= size()) is true, then this method will throw an
     * IndexOutOfBoundsException.
     * 
     * @param index index of the element to retrieve
     * @return T element at the index
     */
    public T get(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        return entries[index];
    }

    
    /**
     * Resizes the entries array.
     * 
     * @param size new size of the array
     */
    private void resize(int size) {
        T[] resized = Arrays.copyOf(entries, size);
        entries = resized;
    }

    
    /** 
     * Swaps two elements (a, b) in the entries array by their index.
     * 
     * @param a index of element a
     * @param b index of element b
     */
    private void swap(int a, int b) {
        T temp = entries[a];
        entries[a] = entries[b];
        entries[b] = temp;
    }
}