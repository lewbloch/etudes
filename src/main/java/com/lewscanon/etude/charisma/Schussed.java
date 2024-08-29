/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.charisma;

import java.util.List;

public class Schussed {
    public static char SCHUSS = 'ß';
    public static char BETA = 'β';

    static final List<String> testData = List.of("З0", "0З");

    public static void main(String... args) {
        System.out.printf("Does '%c' == '%c'? %b\n", SCHUSS, BETA, SCHUSS == BETA);

        for (String numeral : testData) {
            try {
                final int parsed = Integer.parseInt(numeral);
                System.out.printf("\"%s\" -> %d%n", numeral, parsed);
            }
            catch (NumberFormatException exc) {
                System.out.printf("\"%s\" -> %s: %s%n", numeral,
                        exc.getClass().getSimpleName(), exc.getMessage());
            }
        }

    }
}
