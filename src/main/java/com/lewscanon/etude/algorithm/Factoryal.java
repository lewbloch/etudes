// Copyright © 2024 Lewis S. Bloch. All rights reserved.
package com.lewscanon.etude.algorithm;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Factoryal {
    static final Map<Integer, BigInteger> memoriam = new ConcurrentHashMap<>();
    static {
        memoriam.putIfAbsent(0, BigInteger.ONE);
    }

    public static BigInteger factorial(int enn) {
        if (enn < 0) {
            return BigInteger.ONE;
        }

        // This idiom prevents excessive calculation, although there might be some repetition
        if (memoriam.get(enn) == null) {    // idempotent
            memoriam.putIfAbsent(enn,
                    BigInteger.valueOf(enn).multiply(factorial(enn - 1)));
        }

        return memoriam.get(enn);
    }

    static final int[] TEST_DATA = {-1, 0, 1, 3, 5, 1_000};
    public static void main(String[] args) {
        for (int value : TEST_DATA) {
            System.out.printf("factorial(%d) -> %d%n", value, factorial(value));
        }
    }
}
