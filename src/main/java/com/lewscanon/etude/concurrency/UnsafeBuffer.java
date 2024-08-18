/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Proof that {@link StringBuffer} is not thread safe.
 * @param input {@link StringBuffer} won't save you.
 */
public record UnsafeBuffer(String input) {
  static final String FMT = "%s\n%s\n\n";
  static final String INJECTED = " ";
  static final int NUMTRIALS = 12;
  static final String DEFAULT = "";

  /**
   * Instance owns input.
   * @param input {@link String} to reassemble.
   */
  public UnsafeBuffer(final String input) {
    this.input = Optional.of(input).orElse("");
  }

  /**
   * Buffer and reassemble the input.
   * @return the reassambled {@link String}.
   */
  public String reassemble() {
    assert input != null;
    if (input.isBlank()) {
      return DEFAULT;
    }

    final List<String> pieces = List.of(input.split(INJECTED));
    if (pieces.isEmpty()) {
      return DEFAULT;
    }

    final StringBuffer unsafe = new StringBuffer();

    final List<Thread> pool = new ArrayList<>(pieces.size());
    for (var piece : pieces) {
      pool.add(new Thread(() -> unsafe.append(piece).append(INJECTED)));
    }

    for (var thread : pool) {
      thread.start();
    }
    for (var thread : pool) {
      try {
        thread.join();
      }
      catch (InterruptedException exc) {
        Thread.currentThread().interrupt();
      }
    }
    return unsafe.toString();
  }

  @Override
  public String input() {
    return input;
  }

  static final List<String> testData = List.of(
"Now it is a time for all good humans to come to the aid of some other humans."
  );

  /**
   * Try it.
   * @param args arguments.
   */
  public static void main(String... args) {
    for (var data : args.length == 0? testData : Arrays.asList(args)) {
      for (int trial = 0; trial < NUMTRIALS; ++trial) {
        final UnsafeBuffer unsafeBuffer = new UnsafeBuffer(data);
        System.out.printf(FMT, unsafeBuffer.input(), unsafeBuffer.reassemble());
      }
    }
  }
}
