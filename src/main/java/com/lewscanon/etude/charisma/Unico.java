// Copyright © 2023 Lewis S. Bloch. All rights reserved.
package com.lewscanon.etude.charisma;

import static java.lang.Character.*;

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
     * Demonstrate some aspects of code points.
     * @param args program arguments.
     */
    public static void main(String... args) {
        showCodePoints();
    }
}
