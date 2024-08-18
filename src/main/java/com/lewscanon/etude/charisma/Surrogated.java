/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.charisma;

public class Surrogated {
    public static void main(String... args) {
        final String cleffed = "\uD834\uDD1E\uD834\uDD1A\uD834\uDD07 Sing it!";
        System.out.printf("\"%s\" %d characters\n", cleffed,
            cleffed.length());
        System.out.printf("\"%s\" %d codepoints\n", cleffed,
            cleffed.codePointCount(0, cleffed.length()));

        final String FMT = "%c ";
        for (int ix = 0; ix < cleffed.length(); ix++) {
            char ch = cleffed.charAt(ix);
            System.out.printf(FMT, Character.toUpperCase(ch));
        }
        System.out.println();
        String reversed = new StringBuilder(cleffed).reverse().toString();
        System.out.printf("\"%s\" %d codepoints\n", reversed,
                reversed.codePointCount(0, reversed.length()));

    }
}
