import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap implementation a Cache ADT.
 *
 * @author Digno JR Teogalbo
 * @version CS321-001 Fall 22
 */
public class CacheLinkedHashMap<T> {
    private int size;
    private LinkedHashMap<String, T> list;
    private int references = 0;
    private int hits = 0;
    private static final float loadFactor = 1f; // resize unless completely full
    private static final boolean accessOrder = false; // for insertion-order

    /**
     * Constructs a cache with a maximum size with a hashmap with load factor of 1f and insertion order.
     * Specific constructor for caches is used (as specified in JavaDocs) and overrides removeEldestEntry
     * to remove entries that will exceed the maximum size.
     * 
     * @param size Maximum size of the cache
     */
    public CacheLinkedHashMap(int size) {
        this.size = size;
        list = new LinkedHashMap<>(size + 1, loadFactor, accessOrder) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, T> eldest) {
                return list.size() > size;
            }
        };
    }

    /**
     * Retrieves the object from cache and moves to the front of the cache.
     * If the object does not exist in the cache, place the object to the front and
     * return the object.
     * 
     * @param object Object to retrieve from cache
     * @return T Object from cache
     */
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

    /**
     * Adds the object to the front of the cache. If adding the object will exceed
     * the maximum size, remove the last object in cache.
     * 
     * @param object Object to add to cache
     */
    public void addObject(T object) {
        list.put(object.toString(), object);
    }

    /**
     * Removes the object from cache.
     * 
     * @param object Object to remove from cache
     */
    public void removeObject(T object) {
        list.remove(object.toString());
    }

    /**
     * Empties all entries from cache.
     */
    public void clearCache() {
        list.clear();
    }

    /**
     * Returns a string with the number of cache entries, cache references, cache
     * hits, and the 1st-level cache hit ratio.
     * 
     * @return String String containing the cache statistics.
     */
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

    /**
     * Returns the maximum size of the cache.
     * 
     * @return int Maximum size of the cache
     */
    public int size() {
        return size;
    }

    /**
     * Returns the number of references to the cache.
     * 
     * @return int Number of cache references
     */
    public int getReferences() {
        return references;
    }

    /**
     * Returns the number of hits to the cache.
     * 
     * @return int Number of cache hits
     */
    public int getHits() {
        return hits;
    }
}
