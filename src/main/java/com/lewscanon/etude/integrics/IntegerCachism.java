/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.integrics;

import java.util.List;

/** Demo the {@link Integer} cache. */
@SuppressWarnings({"NumberEquality", "ConstantValue", "WrapperTypeMayBePrimitive"})
public class IntegerCachism {
  static final String FMT = "%s: %s\n";
  static final String SCENARIO = "%d by %s: %b\n";

  /**
   * Contrast {@link Integer} {@code ==} and {@code equals} comparison.
   * @param testData values to test.
   */
public static void testCache(List<Integer> testData) {
  for (int value : testData) {
    final Integer first = value;
    final Integer second = value;
    System.out.printf(SCENARIO, value, "  ==  ", first == second);
    System.out.printf(SCENARIO, value, "equals", first.equals(second));
    System.out.println();
  }
}

  /**
   * Drive the demo.
   * @param args command arguments.
   */
public static void main(String... args) {
  List<Integer> testData = List.of(0, 127, 128, 1023, 2047, 2049);
  System.out.printf(FMT, "testData", testData);
  testCache(testData);
}
}
