/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.syntactics;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class TestingOptional {
  static final Map<Optional<String>, String> testData = Map.of(
    Optional.of("hello"), "HELLO",
    Optional.empty(), "",
    Optional.of(""), "",
    Optional.of("123"), "123",
    Optional.of("aB3!eFg"), "AB3!EFG"
  );

  static final String OUFMT = "%-18s -> %8s ? %-5b -> \"%s\"%n";

  public static void main(String... args) {

    final Function<String, String> toUpperCaseFunction = String::toUpperCase;

    for (Optional<String> scenario : testData.keySet()) {
      final String expected = testData.get(scenario);

      Optional<String> result = scenario.map(toUpperCaseFunction)
          .filter(s -> s.equals(expected));

      System.out.printf(OUFMT,
          scenario, expected, result.isPresent(), result.orElse(""));
    }
  }
}
