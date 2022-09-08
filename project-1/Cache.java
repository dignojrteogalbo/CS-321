import java.text.DecimalFormat;
import java.util.LinkedList;

public class Cache<T> {
    private int size;
    private LinkedList<T> list;
    private int references = 0;
    private int hits = 0;

    public Cache(int size) {
        this.size = size;
        list = new LinkedList<>();
    }

    public T getObject(T object) {
        references++;
        if (list.contains(object)) {
            hits++;
            list.remove(object);
        } else if (list.size() >= size) {
            list.removeLast();
        }

        list.addFirst(object);
        return list.getFirst();
    }

    public void addObject(T object) {
        if (list.size() >= size) {
            list.removeLast();
        }

        list.addFirst(object);
    }

    public void removeObject(T object) {
        list.remove(object);
    }

    public void clearCache() {
        list = new LinkedList<>();
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("############.############");

        output.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        output.append(String.format("LinkedList Cache with %d entries has been created\n", this.size()));
        output.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        output.append(String.format("Total number of references:\t%d\n", this.getReferences()));
        output.append(String.format("Total number of cache hits:\t%d\n", this.getHits()));
        output.append(String.format("1st-level cache hit ratio:\t%s\n",
                decimalFormat.format((double) hits / (double) references)));

        return output.toString();
    }

    public int size() {
        return size;
    }

    private int getReferences() {
        return references;
    }

    private int getHits() {
        return hits;
    }
}