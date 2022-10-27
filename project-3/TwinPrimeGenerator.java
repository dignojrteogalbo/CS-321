public class TwinPrimeGenerator {
    
    /** 
     * Driver program for the twin prime generator.
     * 
     * @param args program arguments
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            printUsage();
            return;
        }

        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = generateTwinPrimes(a, b);

        if (c > 0) {
            System.out.printf("TwinPrimeGenerator: Found twin primes between %d and %d\n\tResult: %d, %d\n", a, b, c, c + 2);
        } else {
            System.out.printf("TwinPrimeGenerator: Unable to find twin primes between %d and %d\n", a, b);
        }
    }

    /**
     * Prints usage instructions for twin prime generator.
     */
    public static void printUsage() {
        System.out.println("Usage: java TwinPrimeGenerator <lower bound> <upper bound>");
        System.out.println("\tGenerates two twin primes between the inclusive range [lower bound, upper bound]");
        System.out.println("\t<lower bound> => inclusive lower integer bound");
        System.out.println("\t<upper bound> => inclusive upper integer bound");
    }

    
    /** 
     * Returns the smaller of two twin primes between the inclusive range of [start, end].
     * If no twin primes are found return -1.
     * 
     * @param start lower bound to search
     * @param end   upper bound to search
     * @return int  smaller of the twin primes within [start, end], otherwise -1
     */
    public static int generateTwinPrimes(int start, int end) {
        for (int i = start; i <= end - 2; i++) {
            if (isPrime(i) && isPrime(i + 2)) {
                return i;
            }
        }

        return -1;
    }

    
    /** 
     * Returns true or false whether or not the given number is a prime number.
     * 
     * @param number   number to check if prime
     * @return boolean true if prime, otherwise false
     */
    private static boolean isPrime(int number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}