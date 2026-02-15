package com.lewscanon.etude.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.sqrt;

/** Find all primes < n.
 * <a href="https://www.geeksforgeeks.org/dsa/segmented-sieve/">Segmented Sieve</a>
 */
public class Primos {
    /** Find all primes smaller than 'limit'
     * using simple sieve of Eratosthenes. It also stores
     * found primes in List prime[].
     * @param limit Highest value to examine.
     * @param primes Found primes.
     */
    static void simpleSieve(int limit, List<Integer> primes) {
        // Create a boolean array "mark[0..n-1]" and initialize
        // all entries of it as true. A value in mark[p] will
        // finally be false if 'p' is Not a prime, else true.
        boolean[] marks = new boolean[limit + 1];
        Arrays.fill(marks, true);

        for (int upper = (int) sqrt(limit), prx = 2; prx <= upper; prx++) {
            if (marks[prx]) {   // If prx is not changed, then it is a prime
                                // Update all multiples of p
                for (int i = prx * prx; i < limit; i += prx) {
                    marks[i] = false;
                }
            }
        }


        for (int prx = 2; prx < limit; prx++) { // Print all primes, store them in prime
            if (marks[prx]) {
                primes.add(prx);
                System.out.print(prx + "  ");
            }
        }
    }

    /** Prints all prime numbers smaller than 'n'.
     * @param n largest number to examine.
     */
    static void segmentedSieve(int n) {
        // Compute all primes <= square root of n with simple sieve
        final List<Integer> primes = new ArrayList<>();
        final int limit = (int) sqrt(n) + 1;
        simpleSieve(limit, primes);

        // Divide the range [0..n-1] in different segments
        // We have chosen segment size as sqrt(n).
        // While all segments of range [0..n-1] are not processed,
        // process one segment at a time
        for (int low = limit, high = 2 * limit;
                low < n;
                low += limit, high += limit) {
            if (high >= n) {
                high = n;
            }

            // Mark primes in current range.
            // A value in mark[i] will finally be
            // false if 'prx-low' is not prime, else true.
            boolean[] mark = new boolean[limit + 1];
            Arrays.fill(mark, true);

            // Use the found primes by simpleSieve() to find primes in current range
            for (Integer prime : primes) {
                /*  Find the minimum number in [low..high] that is
                    a multiple of prime.get(i) (divisible by prime.get(i))
                    For example, if low is 31 and prime.get(i) is 3,
                    we start with 33.
                 */
                int loLim = ((low / prime) * prime);
                if (loLim < low) {
                    loLim += prime;
                }

                /*  Mark multiples of prime in [low..high]:
                    We are marking prx - low for prx, i.e. each number
                    in range [low, high] is mapped to [0, high-low]
                    so if range is [50, 100]  marking 50 corresponds
                    to marking 0, marking 51 corresponds to 1 and
                    so on. In this way we need to allocate space only
                    for range.
                 */
                for (int prx = loLim; prx < high; prx += prime) {
                    mark[prx - low] = false;
                }
            }

            // Numbers which are not marked as false are prime
            for (int prx = low; prx < high; prx++) {
                if (mark[prx - low]) {
                    primes.add(prx);
                    System.out.print(prx + "  ");
                }
            }
        }
    }

    /** Driver method. */
    public static void main(String[] args) {
        int[] candidates = {100, 1000, 10_000};
        for (int candidate : candidates) {
            System.out.printf("Primes smaller than %d:\n", candidate);
            segmentedSieve(candidate);
            System.out.println();
        }
    }

}
