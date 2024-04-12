/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.syntactics;

interface Sammy {
    CharSequence name();

    default CharSequence properName() {
        return new StringBuilder("Honored ").append(name());
    }
}

interface Samson extends Sammy {
    @Override
    String name();

    @Override
    default String properName() {
        return "Respected " + name();
    }
}

interface Demide extends Sammy {  // not a SAM interface
    @Override
    default String name() {
        return "Demi";
    }

    @Override
    default String properName() {
        return "Noticed " + name();
    }
}

public class SamChildren {
    static final String FMT = "name() -> \"%s\"\nproperName() -> \"%s\"\n";

    public static void main(String[] args) {
        final Sammy sammie = new Charlie();
        final Sammy fammie = () -> "Sammy";
        final Samson famson = () -> "Samson";
        final Sammy darmie = new Dharmy();
//    final Demide darker = () -> "Darkened"; // will not compile

        final CharSequence result = sammie.name();
        System.out.printf(FMT, result, sammie.properName());

        final CharSequence fammout = fammie.name();
        System.out.printf(FMT, fammout, fammie.properName());

        final CharSequence famsout = famson.name();
        System.out.printf(FMT, famsout, famson.properName());

        final CharSequence darhout = darmie.name();
        System.out.printf(FMT, darhout, darmie.properName());
    }
}

class Charlie implements Samson {
    @Override
    public String name() {
        return "Charlie";
    }

    @Override
    public String properName() {
        return "Noticed " + name();
    }
}

class Dharmy implements Demide {
    @Override
    public String name() {
        return "Dharma";
    }

    @Override
    public String properName() {
        return "Denoted " + name();
    }
}
