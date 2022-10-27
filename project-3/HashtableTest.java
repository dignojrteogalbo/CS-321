import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.util.Date;

/**
 * Program to test Hashtable with varying data types in order to see average probe count, duplicates, and sizes.
 * 
 * @author Digno JR Teogalbo
 * @version CS 321 Fall 2022
 */
public class HashtableTest {
    private static final Random RANDOM = new Random();

    /**
     * Prints the usage instructions for this program.
     */
    public static void printUsage() {
        System.out.println("Usage: java HashtableTest <dataType> <loadFactor> [<debugLevel>]");
        System.out.println("\t<dataSource>: 1 ==> random numbers");
        System.out.println("\t\t      2 ==> date value as a long");
        System.out.println("\t\t      3 ==> word list");
        System.out.println("\t<loadFactor>: The ratio of objects to table size, ");
        System.out.println("\t\t      denoted by alpha = n/m");
        System.out.println("\t<debugLevel>: 0 ==> print summary of experiment");
        System.out.println("\t\t      1 ==> save the two hash tables to a file at the end");
        System.out.println("\t\t      2 ==> print debugging output for each insert");
    }

    
    /** 
     * Inserts random integers into a hashtable until the the load factor is reached
     * and returns the number of inserts performed.
     * 
     * @param hashtable   hashtable to insert words into
     * @param loadFactor  load factor to reach
     * @param debugLevel  level of program output
     * @return int number of inserts performed
     */
    public static int testIntegers(Hashtable hashtable, double loadFactor, int debugLevel) {
        int index;
        int insertsPerformed = 0;
        double currentLoad = 0;

        System.out.printf("HashtableTest: Input: Random-Integers Loadfactor: %.2f\n", loadFactor);

        while (currentLoad < loadFactor) {
            HashObject<Integer> object = new HashObject<>(RANDOM.nextInt());
            index = hashtable.insert(object);
            insertsPerformed++;
            currentLoad = hashtable.getLoadFactor();

            if (debugLevel > 1) {
                System.out.printf("\ttable[%d]: %s, currentLoad: %.2f\n", index, object.toString(), currentLoad);
            }
        }

        return insertsPerformed;
    }

    
    /** 
     * Inserts date longs into a hashtable until the the load factor is reached
     * and returns the number of inserts performed.
     * 
     * @param hashtable   hashtable to insert words into
     * @param loadFactor  load factor to reach
     * @param debugLevel  level of program output
     * @return int number of inserts performed
     */
    public static int testDates(Hashtable hashtable, double loadFactor, int debugLevel) {
        int index;
        int insertsPerformed = 0;
        double currentLoad = 0;
        long current = new Date().getTime();

        System.out.printf("HashtableTest: Input: Date-Longs Loadfactor: %.2f\n", loadFactor);

        while (currentLoad < loadFactor) {
            current += 1000;
            Date date = new Date(current);
            HashObject<Long> object = new HashObject<>(date.getTime());
            index = hashtable.insert(object);
            insertsPerformed++;
            currentLoad = (double) hashtable.getSize() / (double) hashtable.getCapacity();

            if (debugLevel > 1) {
                System.out.printf("\ttable[%d]: %s, currentLoad: %.2f\n", index, object.toString(), currentLoad);
            }
        }

        return insertsPerformed;
    }

    
    /** 
     * Inserts words from "word-list" file into a hashtable until the the load factor is reached
     * and returns the number of inserts performed.
     * 
     * @param hashtable   hashtable to insert words into
     * @param loadFactor  load factor to reach
     * @param debugLevel  level of program output
     * @return int number of inserts performed
     */
    public static int testWords(Hashtable hashtable, double loadFactor, int debugLevel) {
        int index;
        int insertsPerformed = 0;
        double currentLoad = 0;
        File wordList = new File("word-list");

        System.out.printf("HashtableTest: Input: Word-List Loadfactor: %.2f\n", loadFactor);

        try (Scanner finput = new Scanner(wordList)) {
            while (finput.hasNextLine() && currentLoad < loadFactor) {
                String word = finput.nextLine();
                HashObject<String> object = new HashObject<>(word);
                index = hashtable.insert(object);
                insertsPerformed++;
                currentLoad = hashtable.getLoadFactor();

                if (debugLevel > 1) {
                    System.out.printf("\ttable[%d]: %s, currentLoad: %.2f\n", index, object.toString(), currentLoad);
                }
            }
        } catch (FileNotFoundException err) {
            System.out.printf("HashtableTest: File \"word-list\" not found!");
        } catch (Exception err) {
            System.out.println("HashtableTest: Exception encountered when trying to access file \"word-list\"!");
        }

        return insertsPerformed;
    }

    
    /** 
     * Dumps the file contents to a file from a given hashtable
     * 
     * @param hashtable hashtable to dump contents
     * @param filename  filename of output file
     */
    public static void generateFile(Hashtable hashtable, String filename) {
        try {
            FileWriter fout = new FileWriter(filename);
            PrintWriter out = new PrintWriter(new BufferedWriter(fout));
            HashObject[] entries = hashtable.getArray();

            for (int i = 0; i < hashtable.getCapacity(); i++) {
                if (entries[i] != null) {
                    out.println(String.format("table[%d]: %s" , i, entries[i].toString()));
                }
            }
            
            out.close();
            fout.close();
        } catch (IOException err) {
            System.err.println("HashtableTest: Exception occurred when writing to file!");
            System.err.println(err);
        } finally {
            System.out.println("HashtableTest: Saved dump of hash table");
        }
    }

    
    /** 
     * Runs the test for a single hashtable and prints the results from a given data type, load factor, and debug level.
     * 
     * @param dataType   int selection from 1, 2, or 3
     * @param loadFactor ratio of object to capacity to fill the hashtable
     * @param debugLevel level of program output
     * @param hashtable  hashtable to perform tests on
     */
    public static void runTests(int dataType, double loadFactor, int debugLevel, Hashtable hashtable) {
        int insertsPerformed;

        if (hashtable instanceof LinearProbing) {
            System.out.printf("\tUsing Linear Probing\n");
        } else {
            System.out.printf("\tUsing Double Hashing\n");
        }

        switch (dataType) {
            case 1:
                insertsPerformed = testIntegers(hashtable, loadFactor, debugLevel);
                break;
            case 2:
                insertsPerformed = testDates(hashtable, loadFactor, debugLevel);
                break;
            case 3:
                insertsPerformed = testWords(hashtable, loadFactor, debugLevel);
                break;
            default:
                System.out.println("HashtableTest: Invalid selection for data type!");
                printUsage();
                return;
        }

        System.out.printf("HashtableTest: size of hash table is %d\n", hashtable.getSize());
        System.out.printf("               Inserted %d elements, of which %d were duplicates\n", insertsPerformed, hashtable.getDuplicates());
        System.out.printf("               Avg. no. of probes = %.2f\n\n", (double) hashtable.getProbes() / (double) hashtable.getSize());

        if (debugLevel > 0) {
            if (hashtable instanceof LinearProbing) {
                generateFile(hashtable, "linear-dump.txt");
            } else {
                generateFile(hashtable, "double-dump.txt");
            }
        }
    }

    
    /** 
     * Driver method for the HashtableTest program
     *      <dataType>   => 1 for random integers
     *                    2 for date longs
     *                    3 for strings in word list
     *      <loadFactor> => 0 < a < 1 where the
     *                     a = n/m for n = number of objects,
     *                     and m = size of Hashtable capacity
     *      [debugLevel] => 0 is default and prints the program summary
     *                      1 dumps the contents of the hashtable to a file
     *                      2 prints insertions per element to the console
     * 
     * @param args program arguments for HashtableTest
     */
    public static void main(String[] args) { 
        if (args.length < 2) {
            printUsage();
            return;
        }

        int dataType = Integer.parseInt(args[0]);
        double loadFactor = Double.parseDouble(args[1]);
        int debugLevel = args.length >= 3 ? Integer.parseInt(args[2]) : 0;

        int tableCapacity = TwinPrimeGenerator.generateTwinPrimes(95500, 96000) + 2;
        System.out.printf("HashtableTest: Found a twin prime table capacity: %d\n", tableCapacity);
        LinearProbing linearProbing = new LinearProbing(tableCapacity);
        DoubleHashing doubleHashing = new DoubleHashing(tableCapacity);

        runTests(dataType, loadFactor, debugLevel, linearProbing);
        runTests(dataType, loadFactor, debugLevel, doubleHashing);
    }
}