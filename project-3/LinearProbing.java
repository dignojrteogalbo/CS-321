public class LinearProbing extends Hashtable {
    public LinearProbing(double loadFactor) {
        super(loadFactor);
    }

    public LinearProbing(double loadFactor, int initialCapacity) {
        super(loadFactor, initialCapacity);
    }

    @Override
    public int hash(Object object, int iterations) {
        int k = object.hashCode();
        int h1 = positiveMod(k, entries.length);
        int hash = positiveMod(h1 + iterations, entries.length);

        return hash;
    }
}