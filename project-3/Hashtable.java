public abstract class Hashtable {
    protected HashObject[] entries;
    protected int size;
    protected double loadFactor;
    private TwinPrimeGenerator primeGenerator = new TwinPrimeGenerator();

    public static void main(String[] args) {
        LinearProbing lp = new LinearProbing(0.5, 10);
        lp.insert(new HashObject<Integer>(1));
        lp.insert(new HashObject<Integer>(1));

    }

    protected Hashtable(double loadFactor) {
        this.loadFactor = loadFactor;
        entries = new HashObject[(int) (primeGenerator.generateTwinPrimes(95500, 96000) * loadFactor)];
    }

    protected Hashtable(double loadFactor, int initialCapacity) {
        this.loadFactor = loadFactor;
        entries = new HashObject[initialCapacity];
    }

    public int getCapacity() {
        return entries.length;
    }

    public int getSize() {
        return size;
    }

    public int getProbes() {
        int probes = 0;

        for (HashObject object : entries) {
            if (object != null) {
                probes += object.getProbes();
            }
        }

        return probes;
    }

    public int getDuplicates() {
        int duplicates = 0;

        for (HashObject object : entries) {
            if (object != null) {
                duplicates += object.getFrequency();
            }
        }

        return duplicates;
    }

    public HashObject search(HashObject object) {
        int i = 0;

        while (i < entries.length) {
            int index = hash(object, i);

            if (entries[index] == null) {
                return null;
            } else if (entries[index] != null) {
                if (entries[index].equals(object)) {
                    return entries[index];
                }
            }

            i++;
        }

        return null;
    }

    public void insert(HashObject object) {
        int i = 0;

        while (i < entries.length) {
            int index = hash(object.getKey(), i);
            object.incrementProbe();

            if (entries[index] == null) {
                entries[index] = object;
                size++;
                return;
            } else if (entries[index].equals(object)) {
                entries[index].incrementFrequency();
                return;
            }

            i++;
        }
    }

    public void delete(HashObject object) {
        int i = 0;

        while (i < entries.length) {
            int index = hash(object, i);

            if (entries[index] != null) {
                if (entries[index].equals(object)) {
                    entries[index].decrementFrequency();
                    entries[index] = null;
                    return;
                }
            }

            object.incrementProbe();
            i++;
        }
    }

    protected int positiveMod(int dividend, int divisor) {
        int quotient = dividend % divisor;

        if (quotient < 0) {
            quotient += divisor;
        }

        return quotient;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < entries.length; i++) {
            if (entries[i] != null) {
                String value = entries[i].getKey().toString();
                int frequency = entries[i].getFrequency();
                int probes = entries[i].getProbes();
                String element = String.format("table[%d]: %s %d %d\n", i, value, frequency, probes);
                stringBuilder.append(element);
            }
        }

        return stringBuilder.toString();
    }

    public abstract int hash(Object object, int iterations);
}