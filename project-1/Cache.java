import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 * LinkedList implementation a Cache ADT.
 *
 * @author Digno JR Teogalbo
 * @version CS321-001 Fall 22
 */
public class Cache<T> {
    private int size;
    private LinkedList<T> list;
    private int references = 0;
    private int hits = 0;

    /**
     * Constructs a cache with a maximum size.
     * 
     * @param size Maximum size of the cache
     */
    public Cache(int size) {
        this.size = size;
        list = new LinkedList<>();
    }

    /**
     * Retrieves the object from cache and moves to the front of the cache.
     * If the object does not exist in the cache, place the object to the front and return the object.
     * 
     * @param object Object to retrieve from cache
     * @return T Object from cache
     */
    public T getObject(T object) {
        references++;
        if (list.contains(object)) {
            // remove the object if it exists
            hits++;
            list.remove(object);
        } else if (list.size() >= size) {
            // remove the last object if size exceeds maximum
            list.removeLast();
        }

        // place the object to retrieve to the front
        list.addFirst(object);
        return list.getFirst();
    }

    /**
     * Adds the object to the front of the cache. If adding the object will exceed
     * the maximum size, remove the last object in cache.
     * 
     * @param object Object to add to cache
     */
    public void addObject(T object) {
        if (list.size() >= size) {
            list.removeLast();
        }

        list.addFirst(object);
    }

    /**
     * Removes the object from cache.
     * 
     * @param object Object to remove from cache
     */
    public void removeObject(T object) {
        list.remove(object);
    }

    /**
     * Empties all entries from cache.
     */
    public void clearCache() {
        list = new LinkedList<>();
    }
    
    /**
     * Returns a string with the number of cache entries, cache references, cache hits, and the 1st-level cache hit ratio.
     * 
     * @return String String containing the cache statistics.
     */
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
    private int getReferences() {
        return references;
    }

    /** 
     * Returns the number of hits to the cache.
     * 
     * @return int Number of cache hits
     */
    private int getHits() {
        return hits;
    }
}