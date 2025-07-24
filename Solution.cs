
using System;

public class Solution
{
    private static readonly int SMALLEST_PRIME_NUMBER = 2;

    public long SplitArray(int[] input)
    {
        bool[] primes = CreateSieveOfEratosthenes(input.Length);
        return CalculateDifferenceOfSumAtPrimeAndNonprimeIndices(input, primes);
    }

    private bool[] CreateSieveOfEratosthenes(int inputSize)
    {
        bool[] primes = new bool[inputSize];
        Array.Fill(primes, true);

        for (int i = 0; i < Math.Min(SMALLEST_PRIME_NUMBER, inputSize); ++i)
        {
            primes[i] = false;
        }

        int upperLimit = (int)Math.Sqrt(primes.Length);
        for (int i = SMALLEST_PRIME_NUMBER; i <= upperLimit; ++i)
        {
            if (primes[i] == false)
            {
                continue;
            }
            long index = (long)i * i;
            while (index < primes.Length)
            {
                primes[(int)index] = false;
                index += i;
            }
        }
        return primes;
    }

    private long CalculateDifferenceOfSumAtPrimeAndNonprimeIndices(int[] input, bool[] primes)
    {
        long sumAtPrimeIndices = 0;
        long sumAtNonprimeIndices = 0;

        for (int i = 0; i < primes.Length; ++i)
        {
            if (!primes[i])
            {
                sumAtNonprimeIndices += input[i];
            }
            else
            {
                sumAtPrimeIndices += input[i];
            }
        }
        return Math.Abs(sumAtPrimeIndices - sumAtNonprimeIndices);
    }
}
