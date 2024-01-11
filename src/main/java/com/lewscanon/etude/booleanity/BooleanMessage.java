/* Copyright © 2023, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.booleanity;

/* Booleans that reveal their evaluation. */
public class BooleanMessage {
    /**
     * Identify a {@code boolean} and return it.
     * @param description descriptive text.
     * @param value the value to identify.
     * @return the value.
     */
    public static boolean showBool(String description, boolean value) {
        System.out.printf("%s %b%n", description, value);
        return value;
    }

    /**
     * Entry point to try things out.
     * @param args command-line arguments.
     */
    @SuppressWarnings("ConstantValue")
    public static void main(String... args) {
        int value = 5;
        showBool("value > 2 | value < 2 ?",
            showBool("value > 2", value > 2)
                    | showBool("value < 2", value < 2));
        System.out.println();
        showBool("value > 2 || value < 2 ?",
            showBool("value > 2", value > 2)
                    || showBool("value < 2", value < 2));
    }
}
