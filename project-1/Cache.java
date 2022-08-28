import java.util.LinkedList;

public class Cache<T> {
    private LinkedList<T> list;

    public Cache() {
        list = new LinkedList<>();
    }

    public Cache(T object) {
        list = new LinkedList<>();
        list.add(object);
    }

    public T getObject(T object) {
        for (T item : list) {
            if (item.equals(object)) {
                list.remove(item);
                list.addFirst(item);
                return item;
            }
        }
        list.addFirst(object);
        return object;
    }

    public void addObject(T object) {
        list.addFirst(object);
    }

    public void removeObject(T object) {
        list.remove(object);
    }

    public void clearCache() {
        list = new LinkedList<>();
    }

    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {
        Cache<Integer> myCache = new Cache<Integer>();
        myCache.addObject(1);
        myCache.addObject(2);
        myCache.getObject(1);
    }
}