/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.strung;

public class StringleBuilds {
    public static void main(String[] args) {
        final String IDFMT = "%s: (\"%s\", %d)\n%s: (\"%s\", %d)\nequal? %b\t==? %b\n";

        StringBuilder left = new StringBuilder("Alice");
        StringBuilder right = new StringBuilder("Alice");

        System.out.printf(IDFMT,
            "left", left, left.hashCode(),
            "right", right, right.hashCode(),
            left.equals(right), left == right);
    }
}
