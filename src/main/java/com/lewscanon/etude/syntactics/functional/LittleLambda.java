/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.syntactics.functional;

/** Looking at how lambdas are done. */
public class LittleLambda {
    @FunctionalInterface
    interface Lambish {
        boolean evaluate(int expression, int other);
    }

    public static void main(String... args) {
        Lambish validator = (x, y) -> (x > y);
        Lambish valuer = (x, y) -> (x != y);

        System.out.printf("evaluate(%d, %d) -> %b%n",
                47, 30, validator.evaluate(47, 30));
        System.out.printf("evaluate(%d, %d) -> %b%n",
                30, 47, validator.evaluate(30, 47));

        System.out.printf("evaluate(%d, %d) -> %b%n",
                47, 47, valuer.evaluate(47, 47));
        System.out.printf("evaluate(%d, %d) -> %b%n",
                30, 47, valuer.evaluate(30, 47));
    }
}
