import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This program deserializes an ArrayList of players and gets all the player objects into a
 * LinkedList Cache and a LinkedHashMap Cache as well as records the time elapsed to run the 
 * program.
 *
 * @author Digno JR Teogalbo
 * @version CS321-001 Fall 22
 */
public class CacheTest {
    /**
     * Tests the LinkedHashMap Cache and records the time taken to populate the cache.
     * The results of the test will be output to the console.
     *
     * @param cache LinkedHashMap Cache to populate
     * @param players List of player objects
     */
    public static void testCacheLinkedHashMap(CacheLinkedHashMap<Player> cache, List<Player> players) {
        DecimalFormat decimalFormat = new DecimalFormat("############.############");
        long timeStart = System.currentTimeMillis();

        for (Player player : players) {
            cache.getObject(player);
        }

        long duration = System.currentTimeMillis() - timeStart;

        System.out.print(cache.toString());
        System.out.println("----------------------------------------------------------------");
        System.out.printf("Time elapsed: %s.0 milliseconds\n", decimalFormat.format(duration));
        System.out.println("----------------------------------------------------------------");
    }

    /**
     * Tests the LinkedList Cache and records the time taken to populate the cache.
     * The results of the test will be output to the console.
     *
     * @param cache LinkedList Cache to populate
     * @param players List of player objects
     */
    public static void testCacheLinkedList(Cache<Player> cache, List<Player> players) {
        DecimalFormat decimalFormat = new DecimalFormat("############.############");
        long timeStart = System.currentTimeMillis();

        for (Player player : players) {
            cache.getObject(player);
        }

        long duration = System.currentTimeMillis() - timeStart;

        System.out.print(cache.toString());
        System.out.println("----------------------------------------------------------------");
        System.out.printf("Time elapsed: %s.0 milliseconds\n", decimalFormat.format(duration));
        System.out.println("----------------------------------------------------------------");
    }

    /**
     * Prints the usage for CacheTest.
     */
    public static void printUsage() {
        System.out.println("Usage: java CacheTest <cache-size> <serialized-data-filename>");
        System.out.println("=============================================================");
        System.out.println("<cache-size>\tThe size of the cache.");
        System.out.println("<serialized-data-filename>\tThe name of the data file containing serialized Player objects that are being cached");
    }

    /**
     * Runs the CacheTest program based on the given arguments.
     *
     * @param args Array of program arguments
     */
     @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int size;
        ArrayList<Player> players;

        if (args.length < 2) {
            printUsage();
            return;
        }

        try (FileInputStream fileInput = new FileInputStream(args[1])) {
            // read the file from filename
            size = Integer.parseInt(args[0]);
            ObjectInputStream data = new ObjectInputStream(fileInput);
            // deserialize into players list
            players = (ArrayList<Player>) data.readObject();
            data.close();
        } catch (NumberFormatException e) {
            // error if cache-size argument is invalid
            System.out.println(e.getMessage());
            System.out.println("Invalid argument for <cache-size>.\n");
            printUsage();
            return;
        } catch (IOException e) {
            // error if cannot deserialize data file
            System.out.printf("Error reading file: %s\n", args[1]);
            System.out.println(e.getMessage());
            printUsage();
            return;
        } catch (ClassNotFoundException e) {
            // error if Player class is not found
            System.out.println(e.getMessage());
            System.out.println("Player.java class not found!");
            printUsage();
            return;
        }

        // test LinkedList Cache
        Cache<Player> cacheLinkedList = new Cache<>(size);
        testCacheLinkedList(cacheLinkedList, players);

        // test LinkedHashMap Cache
        CacheLinkedHashMap<Player> cacheLinkedHashMap = new CacheLinkedHashMap<>(size);
        testCacheLinkedHashMap(cacheLinkedHashMap, players);
    }
}
