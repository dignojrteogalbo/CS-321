import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class CacheTest {
    private static void printUsage() {
            System.out.println("Usage: java CacheTest <cache-size> <serialized-data-filename>");
            System.out.println("=============================================================");
            System.out.println("<cache-size>\tThe size of the cache.");
            System.out.println("<serialized-data-filename>\tThe name of the data file containing serialized Player objects that are being cached");
    }

    private static <T> Cache<T> createEmptyCache() {
        return new Cache<>();
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            printUsage();
            return;
        }

        int size;
        String filename;
    
        try {
            size = Integer.parseInt(args[0]);
            filename = args[1];
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Invalid argument for <cache-size>.\n");
            printUsage();
            return;
        }

        Cache<Player> emptyCache = createEmptyCache();

        try (FileInputStream fileInput = new FileInputStream(filename)) {
            ObjectInputStream data = new ObjectInputStream(fileInput);
            ArrayList<Player> list = (ArrayList<Player>) data.readObject();
            for (Player player : list) {
                emptyCache.getObject(player);
            }
            System.out.println(emptyCache.toString());
            data.close();
        } catch (IOException e) {
            return;
        } catch (ClassNotFoundException e) {
            return;
        }
    }
}
