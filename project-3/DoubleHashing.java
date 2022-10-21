public class DoubleHashing extends Hashtable {
    public DoubleHashing(double loadFactor) {
        super(loadFactor);
    }

    public DoubleHashing(double loadFactor, int initialCapacity) {
        super(loadFactor, initialCapacity);
    }

    @Override
    public int hash(Object object, int iterations) {
        int k = object.hashCode();
        int h1 = positiveMod(k, entries.length);
        int h2 = 1 + (positiveMod(k, entries.length - 2));
        int hash = positiveMod(h1 + (iterations * h2), entries.length);

        return hash;
    }
}