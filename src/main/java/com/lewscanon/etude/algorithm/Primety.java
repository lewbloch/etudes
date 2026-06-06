package com.lewscanon.etude.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Primety {
    public static void simpleSieve(int limit, List<Integer> primes) {
        if (limit < 2) {
            System.out.printf("Illegal limit %d, must be >= 2\n", limit);
            return;
        }
        if (primes == null) {
            System.out.println("primes must not be null");
            return;
        }

        int root = (int) Math.ceil(Math.sqrt(limit));
        boolean[] marks = new boolean[limit + 1];
        marks[2] = true;
        primes.add(2);

        for (int ix = 3; ix <= limit; ix += 2) {
            marks[ix] = true;
        }
        for (int ix = 3; ix < root; ix += 2) {
            if (marks[ix]) {
                for (int cx = ix * ix; cx <= limit; cx += ix) {
                    marks[cx] = false;
                }
            }
        }
        for (int ix = 3; ix <= limit; ix += 2) {
            if (marks[ix]) {
                primes.add(ix);
            }
        }
    }

    public static List<Integer> segmentedSieve(int enn) {
        List<Integer> answer = new ArrayList<>();

        List<Integer> primes  = new ArrayList<>();
        int limit = (int) Math.ceil(Math.sqrt(enn));
        simpleSieve(limit, primes);
        answer.addAll(primes);

        int lo = limit;
        int hi = limit * 2;

        while (lo <= enn) {
            if (hi > enn) {
                hi = enn;
            }

            boolean[] marks = new boolean[limit + 1];
            Arrays.fill(marks, true);
            for (int ix = 0; ix < primes.size(); ++ix) {
                int prime = primes.get(ix);
                int loLimit = (lo / prime) * prime;
                if (loLimit < lo) {
                    loLimit += prime;
                }
                for (int fx = loLimit; fx < hi; fx += prime) {
                    marks[fx] = false;
                }
            }

            for (int ix = lo; ix <= hi; ++ix) {
                if (marks[ix - lo]) {
                    answer.add(ix);
                }
            }

            lo += limit;
            hi += limit;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] limits = {-1, 0, 1, 2, 3, 4, 11, 17, 39};

        for  (int limit : limits) {
            List<Integer> primes = segmentedSieve(limit);
            System.out.printf("limit %d, primes %s\n", limit, primes);
        }
    }
}
