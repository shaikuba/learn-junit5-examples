package learn.junit5.shopping.params.resolver.base;

import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class RandomUtils {

    public static final int[] intStream = IntStream.range(48, 58).toArray();

    public static final int[] lowerCharStream = IntStream.range(97, 123).toArray();

    public static final int[] upperCharStream = IntStream.range(65, 91).toArray();

    public static final int[] charStream = IntStream.concat(IntStream.range(48, 58), IntStream.range(65, 91)).toArray();

    public static final int[] alphaCharStream = IntStream.concat(IntStream.range(48, 58), IntStream.concat(IntStream.range(65, 91), IntStream.range(97, 123))).toArray();

    public static final Random random = new Random();

    private RandomUtils() {
    }

    /**
     * Generate 32bit character randomly
     *
     * @return uuid with '-' replaced by '' char, for example: 7f0347b9-a33c-48c4-be66-01b85c88be1b to 5fa4ec1fa94e4bb99e0a06cc3910d840
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Generate random number by the min value and max value.
     *
     * @param min included min value.
     * @param max excluded max value.
     * @return
     */
    public static int nextInt(int min, int max) {
        if (max < min) {
            throw new IllegalArgumentException("min < max");
        }
        max -= 1;
        return new Random().nextInt(max) % (max - min + 1) + min;
    }

    /**
     * Generate random number by the min value and max value.
     *
     * @param min included min value.
     * @param max excluded max value.
     * @return
     */
    public static double nextDouble(final double min, final double max) {
        if (max < min) {
            throw new IllegalArgumentException("min < max");
        }
        if (min == max) {
            return min;
        }
        return min + ((max - min) * new Random().nextDouble());
    }

    public static String alphanumeric(int length) {
        return randomChars(alphaCharStream, length);
    }

    public static String letters(int length) {
        return randomChars(charStream, length);
    }

    public static String numeric(int length) {
        return randomChars(intStream, length);
    }

    private static String randomChars(int[] sources, int length) {
        byte[] bytes = new byte[length];
        int bytesWriteIndex = 0;
        for (; length > 0; length--) {
            bytes[bytesWriteIndex++] = (byte) sources[random.nextInt(sources.length)];
        }

        return new String(bytes);
    }

    public static void main(String[] args) {
        System.out.println(alphanumeric(5));
        System.out.println(letters(5));
        System.out.println(numeric(5));
    }
}