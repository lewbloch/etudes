/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.syntactics.initialize;

import static java.lang.System.out;

/** Java myth: Static initializers run before "the" main method. */
public class Statini {
    static final String EVENT = "%s: %s.%s %s%n";

    public static void main(String... args) {
        final String myType = Statini.class.getSimpleName();
        out.printf(EVENT, myType, myType, "main", "started");

        out.printf(EVENT,
            myType, Delayed.class.getSimpleName(), "class", "loaded");
        out.printf(EVENT,
            myType, Delayed.SELF_TYPE, "class", "initialized");
    }
}

class Delayed {
    static final String SELF_TYPE = Delayed.class.getSimpleName();
    static {
        final String localType = Delayed.class.getSimpleName();
        out.printf("* " + Statini.EVENT,
            localType, localType, "class", "initializing");
    }
}
