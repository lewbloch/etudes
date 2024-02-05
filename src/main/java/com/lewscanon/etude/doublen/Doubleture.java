/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.doublen;

/** Demonstrate how IEEE doubles work. */
@SuppressWarnings("ConstantValue")
public class Doubleture {
    static final String LYZE = "%1$s %2$.17f IEEE %2$A %3$X";

    /**
     * Analyze a double expression down to the hex pattern.
     * @param expression what to analyze.
     * @param value its value.
     * @return the analysis, showing the expression, its value as a decimal, and in hex.
     */
    public String reveal(final String expression, final double value) {
        return String.format(LYZE, expression, value, Double.doubleToRawLongBits(value));
    }

    /**
     * Exercise it.
     * @param args command-line arguments.
     */
    public static void main(String... args) {
        final Doubleture revealer = new Doubleture();

        System.out.printf("%s: %b%n", "0.1 + 0.2 == 0.3", (0.1 + 0.2 ) == 0.3);
        System.out.println(revealer.reveal("0.1 + 0.2", 0.1 + 0.2));
        System.out.println(revealer.reveal("      0.3", 0.3));
    }
}
