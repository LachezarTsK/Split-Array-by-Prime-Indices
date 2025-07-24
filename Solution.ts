
function splitArray(input: number[]): number {
    const primes = createSieveOfEratosthenes(input.length);
    return calculateDifferenceOfSumAtPrimeAndNonprimeIndices(input, primes);
};

function createSieveOfEratosthenes(inputSize: number): boolean[] {
    const SMALLEST_PRIME_NUMBER = 2;
    const primes = new Array(inputSize).fill(true);

    for (let i = 0; i < Math.min(SMALLEST_PRIME_NUMBER, inputSize); ++i) {
        primes[i] = false;
    }

    const upperLimit = Math.sqrt(primes.length);
    for (let i = SMALLEST_PRIME_NUMBER; i <= upperLimit; ++i) {
        if (primes[i] === false) {
            continue;
        }
        let index = i * i;
        while (index < primes.length) {
            primes[index] = false;
            index += i;
        }
    }
    return primes;
}

function calculateDifferenceOfSumAtPrimeAndNonprimeIndices(input: number[], primes: boolean[]): number {
    let sumAtPrimeIndices = 0;
    let sumAtNonprimeIndices = 0;

    for (let i = 0; i < primes.length; ++i) {
        if (!primes[i]) {
            sumAtNonprimeIndices += input[i];
        } else {
            sumAtPrimeIndices += input[i];
        }
    }
    return Math.abs(sumAtPrimeIndices - sumAtNonprimeIndices);
}
