
package main
import "math"

const SMALLEST_PRIME_NUMBER = 2

func splitArray(input []int) int64 {
    primes := createSieveOfEratosthenes(len(input))
    return calculateDifferenceOfSumAtPrimeAndNonprimeIndices(input, primes)
}

func createSieveOfEratosthenes(inputSize int) []bool {
    primes := make([]bool, inputSize)
    for i := range primes {
        primes[i] = true
    }

    for i := range min(SMALLEST_PRIME_NUMBER, inputSize) {
        primes[i] = false
    }

    upperLimit := int(math.Sqrt(float64(len(primes))))
    for i := SMALLEST_PRIME_NUMBER; i <= upperLimit; i++ {
        if !primes[i] {
            continue
        }
        index := int64(i) * int64(i)
        for index < int64(len(primes)) {
            primes[index] = false
            index += int64(i)
        }
    }
    return primes
}

func calculateDifferenceOfSumAtPrimeAndNonprimeIndices(input []int, primes []bool) int64 {
    var sumAtPrimeIndices int64 = 0
    var sumAtNonprimeIndices int64 = 0

    for i := range primes {
        if !primes[i] {
            sumAtNonprimeIndices += int64(input[i])
        } else {
            sumAtPrimeIndices += int64(input[i])
        }
    }
    return int64(math.Abs(float64(sumAtPrimeIndices) - float64(sumAtNonprimeIndices)))
}
