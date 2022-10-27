/**
 * Represents an entry in a hashtable with a generic key object, frequency count, and probe count.
 * 
 * @author Digno JR Teogalbo
 * @version CS 321 Fall 2022
 */
public class HashObject<T> {
    private T key;
    private int frequency = 0;
    private int probes = 0;


    /**
     * Generic constructor for a key.
     * 
     * @param key key value for the HashObject to hold
     */
    public HashObject (T key) {
        this.key = key;
    }

    
    /** 
     * Returns the generic key value of the HashObject.
     * 
     * @return key key value of the HashObject
     */
    public T getKey() {
        return key;
    }

    
    /** 
     * Returns the number of probes for this HashObject.
     * 
     * @return int number of probes
     */
    public int getProbes() {
        return probes;
    }

    
    /** 
     * Returns the frequency for this HashObject.
     * 
     * @return int
     */
    public int getFrequency() {
        return frequency;
    }


    /**
     * Increases the probe count by 1
     */
    public void incrementProbe() {
        probes++;
    }


    /**
     * Increases the frequency count by 1
     */
    public void incrementFrequency() {
        frequency++;
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
        return String.format("%s %d %d", key.toString(), frequency, probes);
    }
}