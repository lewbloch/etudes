/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.charisma;

import java.util.List;

public class ReversUni {
    static final List<String> testData = List.of(
        "kayak",
        "Kayaking",
        "Pop🎉 star!",
        "Clapping \uD83D\uDC4F\uD83C\uDFFD hands\uD83D\uDC4F!"
    );

    public String reverse(String input) {
        return switch (input) {
            case null -> null;
            case "" -> input;
            default -> new StringBuilder(input).reverse().toString();
        };
    }

    public String codePointReverse(String input) {
        return switch (input) {
            case null -> null;
            case "" -> input;
            default -> reverseCodePoints(input);
        };
    }

    public String naiveReverse(String input) {
        return switch (input) {
            case null -> null;
            case "" -> input;
            default -> reverseChars(input);
        };
    }

    String reverseChars(String input) {
        final Integer[] reversing = input.chars().boxed().toList().reversed().toArray(new Integer[0]);
        final char[] answer = new char[reversing.length];
        for (int ix = 0; ix < answer.length; ++ix) {
            answer[ix] = (char) reversing[ix].intValue();
        }
        return new String(answer);
    }

    String reverseCodePoints(String input) {
        final Integer[] reversing = input.codePoints().boxed().toList().reversed().toArray(new Integer[0]);
        final int[] answer = new int[reversing.length];
        for (int ix = 0; ix < answer.length; ++ix) {
            answer[ix] = reversing[ix];
        }
        return new String(answer, 0, answer.length);
    }

    public static void main(String... args) {
        final String FORMAT = "\"%s\"%n\"%s\" smart reverse%n\"%s\" naive reverse%n\"%s\" code point reverse%n%n";

        final ReversUni reverser = new ReversUni();
        for (String datum : testData) {
            System.out.printf(FORMAT, datum,
                reverser.reverse(datum),
                reverser.naiveReverse(datum),
                reverser.codePointReverse(datum));
        }
    }
}
