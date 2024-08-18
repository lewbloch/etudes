/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.syntactics.initialize;

/** Refer to a loaded class that never initializes. */
public class Uninitialer {
    static final String INITING = "%s initializing\n";
    static {
        System.out.printf(INITING, Uninitialer.class.getSimpleName());
    }
    public static void main(String... args) {
        System.out.printf("%s main() running\n", Uninitialer.class.getSimpleName());

        System.out.printf("%s value: %d\n", NeverInitialed.class.getSimpleName(), NeverInitialed.value);
    }
}

class Initialed {
    static int value = 10;
    static {
        System.out.printf(Uninitialer.INITING, Initialed.class.getSimpleName());
        value = value-- + --value;
    }
}

class NeverInitialed extends Initialed {
    static {
        System.out.printf(Uninitialer.INITING, NeverInitialed.class.getSimpleName());
        value = --value - value--;
    }
}
