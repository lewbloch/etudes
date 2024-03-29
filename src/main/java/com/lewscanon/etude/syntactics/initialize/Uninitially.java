/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.syntactics.initialize;

public class Uninitially {
    public static void main(String... args) {
        final String FMT = "%s: %d%n";
        System.out.printf(FMT, "NeverSeen.value", NeverSeen.value);
    }
}

class Initialed {
    static int value = 10;
    static {
        System.out.println("Initialed initializing");
        value = value-- + --value;
    }
}

class NeverSeen extends Initialed {
    static {
        System.out.println("NeverSeen initializing");
        //noinspection UnusedAssignment
        value = --value - value--;
    }
}
