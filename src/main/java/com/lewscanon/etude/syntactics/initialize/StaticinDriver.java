 /* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.syntactics.initialize;

/**
 * Java myth: Static initializers run prior to "the" main method.
 * Fact: Most don't.
 * Run this for proof.
 */
public class StaticinDriver {
  static final String EVENT = "%s: %s%n";

  public static void main(String... args) {
    final String driverName = StaticinDriver.class.getSimpleName();
    System.out.printf(EVENT, driverName, "main started");

    final String auxName = Auxiliary.class.getSimpleName();
    System.out.printf(EVENT, driverName, auxName + " loaded, not initialized");

    Auxiliary.announce();
    System.out.printf(EVENT, driverName, auxName + ".announce done");
  }
}

class Auxiliary {
  static final String auxName = Auxiliary.class.getSimpleName();
  static {
    System.out.printf(StaticinDriver.EVENT, auxName, "static initializer");
  }

  static void announce() {
    System.out.printf(StaticinDriver.EVENT, auxName, "announce");
  }
}
