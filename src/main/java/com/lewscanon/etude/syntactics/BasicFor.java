/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.syntactics;

/**
 * Demonstrate the generalized basic {@code for} syntax.
 */
public class BasicFor {
    static final String NOUNCE = "%s%n";
    static final String INIT = "Initial";
    static final String CONDITION = "Condition %d: %5.4f continue? %b%n";
    static final String UPDATE = "Update :%d%n%n";
    static final String BODY = " Body";

    static final double CONTINUE = 0.8333;

    private static int bodyCount = 0;

    /**
     * Initialize conditions for the {@code for}.
     */
    public static void init() {
        System.out.printf(NOUNCE, INIT);
    }

    /**
     * Evaluate the {@code for} condition.
     * @return whether the condition is satisfied.
     */
    public static boolean condition() {
        final double odds = Math.random();
        final var chance = odds < CONTINUE;
        System.out.printf(CONDITION, bodyCount, odds, chance);

        return chance;
    }

    /**
     * Update the {@code for}.
     */
    public static void update() {
        ++bodyCount;
        System.out.printf(UPDATE, bodyCount);
    }

    /**
     * The body of the {@code for}.
     */
    public static void body() {
        System.out.printf(NOUNCE, BODY);
    }

    /**
     * Test the logic.
     * @param args arguments.
     */
    public static void main(String... args) {
        final String TITLE = "Basic For";
        System.out.printf("%s%n%n", TITLE);
        for (init(); condition(); update()) {
            body();
        }
    }
}
