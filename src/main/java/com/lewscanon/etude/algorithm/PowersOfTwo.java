/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.algorithm;

public class PowersOfTwo {
    public boolean isPowerOfTwo(int candid) {
        return (candid & (candid - 1)) == 0 && candid != 0;
    }

    public static void main(String... args) {
        final String FORMAT = "%4d %s%n";

        PowersOfTwo checker = new PowersOfTwo();
        for (int candidate = -1100; candidate < 4200; ++candidate) {
            if (checker.isPowerOfTwo(candidate)) {
                System.out.printf(FORMAT, candidate, Integer.toBinaryString(candidate));
            }
        }
    }
}
