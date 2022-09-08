import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Digno JR Teogalbo
 * @version CS321-001 Fall 22
 */
public class CacheTest {
    
    /** 
     * @param filename
     * @return List<Player>
     */
    @SuppressWarnings("unchecked")
    public static List<Player> getPlayers(String filename) {
        ArrayList<Player> players;
        try (FileInputStream fileInput = new FileInputStream(filename)) {
            ObjectInputStream data = new ObjectInputStream(fileInput);
            players = (ArrayList<Player>) data.readObject();
            data.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Player.java or PlayerGenerator.java classes not found!");
            return new ArrayList<>();
        }

        return players;
    }

    /** 
     * @param cache
     * @param players
     */
    public static void testCache(CacheLinkedHashMap<Player> cache, List<Player> players) {
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
     * @param cache
     * @param players
     */
    public static void testCache(Cache<Player> cache, List<Player> players) {
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

    private static void printUsage() {
            System.out.println("Usage: java CacheTest <cache-size> <serialized-data-filename>");
            System.out.println("=============================================================");
            System.out.println("<cache-size>\tThe size of the cache.");
            System.out.println("<serialized-data-filename>\tThe name of the data file containing serialized Player objects that are being cached");
    }

    /** 
     * @param args
     */
    public static void main(String[] args) {
        int size;
        String filename;

        if (args.length < 2) {
            printUsage();
            return;
        }

        try {
            size = Integer.parseInt(args[0]);
            filename = args[1];
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Invalid argument for <cache-size>.\n");
            return;
        }

        ArrayList<Player> players = (ArrayList<Player>) getPlayers(filename);
        Cache<Player> cacheLinkedList = new Cache<>(size);
        testCache(cacheLinkedList, players);

        CacheLinkedHashMap<Player> cacheLinkedHashMap = new CacheLinkedHashMap<>(size);
        testCache(cacheLinkedHashMap, players);
    }
}
