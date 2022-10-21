public class HashObject<T> {
    private T key;
    private int frequency = 0;
    private int probes = 0;

    public HashObject (T key) {
        this.key = key;
    }

    public Object getKey() {
        return key;
    }

    public int getProbes() {
        return probes;
    }

    public int getFrequency() {
        return frequency;
    }

    public void incrementProbe() {
        probes++;
    }

    public void incrementFrequency() {
        frequency++;
    }

    public void decrementFrequency() {
        frequency--;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof HashObject) {
            HashObject<?> compare = (HashObject<?>) object;
            return key.equals(compare.getKey());
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("Key: %s, Frequency: %d, Probes: %d\n", key.toString(), frequency, probes);
    }
}