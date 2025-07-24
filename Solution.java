
import java.util.Arrays;

public class Solution {

    private static final int SMALLEST_PRIME_NUMBER = 2;

    public long splitArray(int[] input) {
        boolean[] primes = createSieveOfEratosthenes(input.length);
        return calculateDifferenceOfSumAtPrimeAndNonprimeIndices(input, primes);
    }

    private boolean[] createSieveOfEratosthenes(int inputSize) {
        boolean[] primes = new boolean[inputSize];
        Arrays.fill(primes, true);

        for (int i = 0; i < Math.min(SMALLEST_PRIME_NUMBER, inputSize); ++i) {
            primes[i] = false;
        }

        int upperLimit = (int) Math.sqrt(primes.length);
        for (int i = SMALLEST_PRIME_NUMBER; i <= upperLimit; ++i) {
            if (primes[i] == false) {
                continue;
            }
            long index = (long) i * i;
            while (index < primes.length) {
                primes[(int) index] = false;
                index += i;
            }
        }
        return primes;
    }

    private long calculateDifferenceOfSumAtPrimeAndNonprimeIndices(int[] input, boolean[] primes) {
        long sumAtPrimeIndices = 0;
        long sumAtNonprimeIndices = 0;

        for (int i = 0; i < primes.length; ++i) {
            if (!primes[i]) {
                sumAtNonprimeIndices += input[i];
            } else {
                sumAtPrimeIndices += input[i];
            }
        }
        return Math.abs(sumAtPrimeIndices - sumAtNonprimeIndices);
    }
}
