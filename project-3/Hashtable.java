/**
 * Represents a Hashtable which carries HashObjects.
 * 
 * @author Digno JR Teogalbo
 * @version CS 321 Fall 2022
 */
public abstract class Hashtable {
    protected HashObject[] entries;
    protected int size;

    /**
     * Constructor for a hashtable with a given initial capacity.
     * 
     * @param initialCapacity
     */
    protected Hashtable(int initialCapacity) {
        entries = new HashObject[initialCapacity];
    }

    
    /** 
     * Returns the array of entries for this Hashtable.
     * 
     * @return HashObject[] array of HashObject entries
     */
    public HashObject[] getArray() {
        return entries;
    }

    
    /** 
     * Returns the number of unique non-null elements in the Hashtable.
     * 
     * @return int number of unqiue non-null elements
     */
    public int getSize() {
        return size;
    }

    
    /** 
     * Returns the capacity of the Hashtable.
     * 
     * @return int Hashtable capacity
     */
    public int getCapacity() {
        return entries.length;
    }

    
    /** 
     * Returns the total number of probes in the Hashtable.
     * 
     * @return int total number of probes
     */
    public int getProbes() {
        int probes = 0;

        for (HashObject<?> object : entries) {
            if (object != null) {
                probes += object.getProbes();
            }
        }

        return probes;
    }

    
    /** 
     * Returns the number of duplicates in the Hashtable.
     * 
     * @return int number of duplicates
     */
    public int getDuplicates() {
        int duplicates = 0;

        for (HashObject<?> object : entries) {
            if (object != null) {
                duplicates += object.getFrequency();
            }
        }

        return duplicates;
    }

    
    /** 
     * Returns the current load factor of the Hashtable. Load factor is calculated as
     * a = n/m where n is the number of unique non-null elements and m is the 
     * capacity of the Hashtable.
     * 
     * @return double current load factor of this Hashtable
     */
    public double getLoadFactor() {
        return (double) size / (double) entries.length;
    }

    
    /** 
     * Inserts an object into the Hashtable. On new insertions, the Hashtable 
     * updates the probe count for each new object. On insertions with existing 
     * elements, the Hashtable only increments the frequency of the object.
     * 
     * @param object HashObject to insert
     * @return int   index in the Hashtable for a successful insert, otherwise -1
     */
    public int insert(HashObject<?> object) {
        int i = 0;

        while (i < entries.length) {
            int index = hash(object.getKey(), i);
            object.incrementProbe();

            if (entries[index] == null) {
                entries[index] = object;
                size++;
                return index;
            } else if (entries[index].equals(object)) {
                entries[index].incrementFrequency();
                return index;
            }

            i++;
        }

        return -1;
    }

    
    /** 
     * Performs the modulus operator and returns a positive mod of the 
     * dividend and divisor.
     * 
     * @param dividend number to be mod by
     * @param divisor  number to mod the dividend
     * @return int     positive modulus of the dividen and divisor
     */
    protected int positiveMod(int dividend, int divisor) {
        int quotient = dividend % divisor;

        if (quotient < 0) {
            quotient += divisor;
        }

        return quotient;
    }

    /**
     * Open addressing hashing algorithim to insert new elements.
     * 
     * @param object     Object to hash
     * @param iterations number of times to iterate
     * @return           hash of the given object
     */
    public abstract int hash(Object object, int iterations);
}