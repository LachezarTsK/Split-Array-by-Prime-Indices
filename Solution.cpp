
#include <cmath>
#include <vector>
#include <algorithm>
using namespace std;

class Solution {

    static const int SMALLEST_PRIME_NUMBER = 2;

public:
    long long splitArray(vector<int>& input) const {
        vector<bool> primes = createSieveOfEratosthenes(input.size());
        return calculateDifferenceOfSumAtPrimeAndNonprimeIndices(input, primes);
    }

private:
    vector<bool> createSieveOfEratosthenes(int inputSize) const {
        vector<bool> primes(inputSize, true);

        for (int i = 0; i < min(SMALLEST_PRIME_NUMBER, inputSize); ++i) {
            primes[i] = false;
        }

        int upperLimit = sqrt(primes.size());
        for (int i = SMALLEST_PRIME_NUMBER; i <= upperLimit; ++i) {
            if (primes[i] == false) {
                continue;
            }
            long index = (long)i * i;
            while (index < primes.size()) {
                primes[(int)index] = false;
                index += i;
            }
        }
        return primes;
    }

    long long calculateDifferenceOfSumAtPrimeAndNonprimeIndices(span<const int> input, const vector<bool>& primes) const {
        long long sumAtPrimeIndices = 0;
        long long sumAtNonprimeIndices = 0;

        for (int i = 0; i < primes.size(); ++i) {
            if (!primes[i]) {
                sumAtNonprimeIndices += input[i];
            }
            else {
                sumAtPrimeIndices += input[i];
            }
        }
        return abs(sumAtPrimeIndices - sumAtNonprimeIndices);
    }
};
