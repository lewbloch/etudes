/* Copyright © 2026, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.charisma;

public class CharBites {
    static final String QUIZF = "\n\"%s\" test: %b\n";

    public static void main(String... args) {
        final String clefer = "𝄞CDA";
        System.out.printf(QUIZF, clefer, (clefer.getBytes().length & 1) == 0);
//        hint(clefer);
//        hint("BCDA");
//        hint("𝄞C♫♪");
    }

    static void hint(final String clefer) {
        final String HINTF = "\"%s\" count: %d\n\t%s\n";
        byte[] cleflets = clefer.getBytes();
        System.out.printf(HINTF, clefer, cleflets.length, toHexes(cleflets));
    }

    static String toHexes(byte[] barry) {
        if (barry == null) {
            return "";
        }
        final StringBuilder builder = new StringBuilder("[ ");
        for (byte val : barry) {
            builder.append(String.format("%02x ", val));
        }
        builder.append("]");
        return builder.toString();
    }
}

