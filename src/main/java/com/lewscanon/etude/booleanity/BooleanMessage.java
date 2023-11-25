/* Copyright © 2023, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.booleanity;

/* Booleans that reveal their evaluation. */
public class BooleanMessage {
    /**
     * Identify a {@code boolean} and return it.
     * @param express expression to identify the value.
     * @param value the value to identify.
     * @return the value.
     */
    public static boolean setBool(String express, boolean value) {
        System.out.printf(express + " %b%n", value);
        return value;
    }

    /**
     * Entry point to try things out.
     * @param args command-line arguments.
     */
    @SuppressWarnings("ConstantValue")
    public static void main(String... args) {
        int value = 5;
        System.out.printf("value > 2 | value < 2 ? %b%n",
            setBool("value > 2", value > 2) | setBool("value < 2", value < 2));
        System.out.println();
        System.out.printf("value > 2 || value < 2 ? %b%n",
            setBool("value > 2", value > 2) || setBool("value < 2", value < 2));
    }
}
