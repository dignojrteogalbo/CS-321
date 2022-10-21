public class TwinPrimeGenerator {
    public static void main(String[] args) {
        int a = 95500;
        int b = 96000;
        int c = generateTwinPrimes(a, b);
        System.out.printf("twin prime between %d and %d is: (%d, %d)\n", a, b, c, c + 2);
    }

    public static int generateTwinPrimes(int start, int end) {
        for (int i = start; i <= end - 2; i++) {
            if (isPrime(i) && isPrime(i + 2)) {
                return i;
            }
        }

        return -1;
    }

    private static boolean isPrime(int number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}