import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class CacheLinkedHashMap<T> {
    private int size;
    private LinkedHashMap<String, T> list;
    private int references = 0;
    private int hits = 0;
    private static final float loadFactor = 1f; // resize unless completely full
    private static final boolean accessOrder = false; // for insertion-order

    public CacheLinkedHashMap(int size) {
        this.size = size;
        list = new LinkedHashMap<>(size + 1, loadFactor, accessOrder) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, T> eldest) {
                return list.size() > size;
            }
        };
    }

    public T getObject(T object) {
        references++;
        String key = object.toString();

        if (list.containsKey(key)) {
            hits++;
            list.remove(key);
        }

        list.put(key, object);
        return list.get(key);
    }

    public void addObject(T object) {
        list.put(object.toString(), object);
    }

    public void removeObject(T object) {
        list.remove(object.toString());
    }

    public void clearCache() {
        list.clear();
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("############.############");

        output.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        output.append(String.format("LinkedHashMap Cache with %d entries has been created\n", this.size()));
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

    public int getReferences() {
        return references;
    }

    public int getHits() {
        return hits;
    }
}
