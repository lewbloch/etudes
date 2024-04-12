/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.symantics;

import java.util.List;

/**
 * Hidden quirks of text case.
 */
public class ChameleonCase {
    static final List<String> oddCases = List.of("ß", "ς");

    public static void main(String[] args) {
        final String FMT = """
            original: "%s"
             toUpper: "%s"
             toLower: "%s"
            recovered? %b%n
            """;

        for (String oddCase : oddCases) {
            final String uppered = oddCase.toUpperCase();
            final String lowered = uppered.toLowerCase();

            System.out.printf(FMT, oddCase, uppered, lowered, oddCase.equals(lowered));
        }
    }
}
