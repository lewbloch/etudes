/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.algorithm;

public class PowersOfTwo {
    public boolean isPowerOfTwo(int candid) {
        return candid > 0 && (candid & (candid - 1)) == 0;
    }

    public static void main(String... args) {
        final String FORMAT = "%4d %s%n";

        PowersOfTwo checker = new PowersOfTwo();
        for (int candidate = -3; candidate < 1100; ++candidate) {
            if (checker.isPowerOfTwo(candidate)) {
                System.out.printf(FORMAT, candidate, Integer.toBinaryString(candidate));
            }
        }
    }
}
