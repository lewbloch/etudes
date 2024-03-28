/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.syntactics.passby;

public final class Cup {
    private static final String BEVERAGED = "";

    private String beverage;

    public Cup(String beverage) {
        setBeverage(beverage);
    }

    public static void fillCup(Cup cup, String beverage) {
        if (cup != null) {
            cup.setBeverage(beverage);
        }
    }

    @SuppressWarnings({"ParameterCanBeLocal", "UnusedAssignment"})
    public static void referCup(Cup cup, String beverage) {
        cup = new Cup(beverage);
    }

    public static void main(String... args) {
        Cup cup = new Cup("tea");
        fillCup(cup, "coffee");
        System.out.println("Cup is filled with " + cup.getBeverage());

        referCup(cup, "ipecac");
        System.out.println("Cup is filled with " + cup.getBeverage());
    }

    public String getBeverage() {
        assert beverage != null;
        return beverage;
    }

    public void setBeverage(String beverage) {
        this.beverage = beverage == null ? BEVERAGED : beverage;
    }
}
