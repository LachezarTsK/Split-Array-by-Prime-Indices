
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.sqrt

class Solution {

    private companion object {
        const val SMALLEST_PRIME_NUMBER = 2
    }

    fun splitArray(input: IntArray): Long {
        val primes = createSieveOfEratosthenes(input.size)
        return calculateDifferenceOfSumAtPrimeAndNonprimeIndices(input, primes)
    }

    private fun createSieveOfEratosthenes(inputSize: Int): BooleanArray {
        val primes = BooleanArray(inputSize) { true }

        for (i in 0..<min(SMALLEST_PRIME_NUMBER, inputSize)) {
            primes[i] = false
        }

        val upperLimit = sqrt(primes.size.toDouble()).toInt()
        for (i in SMALLEST_PRIME_NUMBER..upperLimit) {
            if (!primes[i]) {
                continue
            }
            var index = i * i.toLong()
            while (index < primes.size) {
                primes[index.toInt()] = false
                index += i
            }
        }
        return primes
    }

    private fun calculateDifferenceOfSumAtPrimeAndNonprimeIndices(input: IntArray, primes: BooleanArray): Long {
        var sumAtPrimeIndices: Long = 0
        var sumAtNonprimeIndices: Long = 0

        for (i in primes.indices) {
            if (!primes[i]) {
                sumAtNonprimeIndices += input[i]
            } else {
                sumAtPrimeIndices += input[i]
            }
        }
        return abs(sumAtPrimeIndices - sumAtNonprimeIndices)
    }
}
