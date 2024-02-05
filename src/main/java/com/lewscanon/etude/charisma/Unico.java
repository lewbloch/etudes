// Copyright © 2023 Lewis S. Bloch. All rights reserved.
package com.lewscanon.etude.charisma;

import static java.lang.Character.isHighSurrogate;
import static java.lang.Character.isLowSurrogate;

/** Explorations of Unicode code points. */
public class Unico {
    static final String HXCPF =
            "{index: %d, charValue: 0x%05x, codePoint: '%c',\thexPoint: 0x%<05x, highSurrogate: %b, lowSurrogate: %b}%n";

    static final String BIRTHDAY = "🎁 Happy Birthday!";

    /**
     * Show how code points relate to {@code String} index.
     */
    public static void showCodePoints() {
        for (int ix = 0; ix < 4; ++ix) {
            final int codePoint = BIRTHDAY.codePointAt(ix);
            final char charPart = BIRTHDAY.charAt(ix);
            System.out.printf(HXCPF,
                    ix, (int) charPart, codePoint, isHighSurrogate(charPart), isLowSurrogate(charPart));
        }
    }

    /**
     * Explore conversions.
     */
    public static void chonvert() {
        short shx = 32_000;
        char chx = (char) shx;

        char chy = 32_000;
        short shy = (short) chx;

        System.out.printf("shx = %d, chx = %d, shy = %d, chy = %d%n",
                shx, (int) chx, shy, (int) chy);
    }

    /**
     * Demonstrate some aspects of code points.
     * @param args program arguments.
     */
    public static void main(String... args) {
        showCodePoints();
        char value = 17000;
        System.out.printf("char value = %c%n", value);

        chonvert();
    }
}
