import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.util.Date;

public class HashtableTest {
    private static final Random RANDOM = new Random();
    private static final TwinPrimeGenerator TWIN_PRIME_GENERATOR = new TwinPrimeGenerator();

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

    public static void runTests(int dataType, double loadFactor, int debugLevel, Hashtable hashtable, String hashtableString) {
        System.out.printf("\tUsing %s\n", hashtableString);
        String dataTypeName;
        double currentLoad = 0;
        int insertsPerformed = 0;

        switch (dataType) {
            case 1:
                dataTypeName = "random-integers";
                System.out.printf("HashtableTest: Input: Random-Integers Loadfactor: %.2f\n", loadFactor);

                while (currentLoad < loadFactor) {
                    HashObject<Integer> object = new HashObject<>(RANDOM.nextInt());
                    hashtable.insert(object);
                    insertsPerformed++;
                    currentLoad = (double) hashtable.getSize() / (double) hashtable.getCapacity();
                }

                break;
            case 2:
                dataTypeName = "sequential-long";
                System.out.printf("HashtableTest: Input: Sequential-Long Loadfactor: %.2f\n", loadFactor);
                long current = new Date().getTime();

                while (currentLoad < loadFactor) {
                    current += 1000;
                    Date date = new Date(current);
                    HashObject<Long> object = new HashObject<>(date.getTime());
                    hashtable.insert(object);
                    insertsPerformed++;
                    currentLoad = (double) hashtable.getSize() / (double) hashtable.getCapacity();
                }

                break;
            case 3:
                dataTypeName = "word-list";
                System.out.printf("HashtableTest: Input: Word-List Loadfactor: %.2f\n", loadFactor);
                File wordList = new File("word-list");

                try (Scanner finput = new Scanner(wordList)) {
                    while (finput.hasNextLine() && currentLoad < loadFactor) {
                        String word = finput.nextLine();
                        HashObject<String> object = new HashObject<>(word);
                        hashtable.insert(object);
                        insertsPerformed++;
                        currentLoad = (double) hashtable.getSize() / (double) hashtable.getCapacity();
                    }
                } catch (FileNotFoundException err) {
                    System.out.printf("HashtableTest: File \"word-list\" not found!");
                } catch (Exception err) {
                    System.out.println("HashtableTest: Exception encountered when trying to access file \"word-list\"!");
                }

                break;
            default:
                System.out.println("HashtableTest: Invalid selection for data type!");
                printUsage();
                return;
        }

        System.out.printf("HashtableTest: size of hash table is %d\n", hashtable.getSize());
        System.out.printf("               Inserted %d elements, of which %d were duplicates\n", insertsPerformed, hashtable.getDuplicates());
        System.out.printf("               Avg. no. of probes = %.2f\n", (double) hashtable.getProbes() / (double) hashtable.getSize());

        if (debugLevel > 0) {
            String hashtableName = hashtableString.split(" ", 2)[0].toLowerCase();
            String filename = String.format("%s-%1.1f-%s-dump.txt", dataTypeName, loadFactor, hashtableName);

            try {
                FileWriter fout = new FileWriter(filename);
                PrintWriter out = new PrintWriter(new BufferedWriter(fout));
                out.println(hashtable.toString());
                out.close();
            } catch (IOException err) {
                System.err.println("HashtableTest: Exception occurred when writing to file!");
                System.err.println(err);
            } finally {
                System.out.println("HashtableTest: Saved dump of hash table");
            }
        }
    }

    public static void main(String[] args) { 
        if (args.length < 2) {
            printUsage();
            return;
        }

        int dataType = Integer.parseInt(args[0]);
        double loadFactor = Double.parseDouble(args[1]);
        int debugLevel = args.length >= 3 ? Integer.parseInt(args[2]) : 0;

        int tableCapacity = TWIN_PRIME_GENERATOR.generateTwinPrimes(95500, 96000) + 2;
        System.out.printf("HashtableTest: Found a twin prime table capacity: %d\n", tableCapacity);
        LinearProbing linearProbing = new LinearProbing(loadFactor, tableCapacity);
        DoubleHashing doubleHashing = new DoubleHashing(loadFactor, tableCapacity);

        runTests(dataType, loadFactor, debugLevel, linearProbing, "Linear Probing");
        System.out.println();
        runTests(dataType, loadFactor, debugLevel, doubleHashing, "Double Hashing");
    }
}