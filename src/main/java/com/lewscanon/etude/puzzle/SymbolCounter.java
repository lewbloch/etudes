/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.puzzle;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/** A thread-safe character (code point) counter for character sequences. */
public class SymbolCounter implements AutoCloseable {
 final Map<Integer, LongAdder> symbolCounts = new ConcurrentHashMap<>();

 @Override
 public void close() {
 }

 /**
  * Count the occurrences of each symbol within an input sequence.
  * @param input the sequence of characters (code points) to analyze.
  */
 public void updateStats(CharSequence input) {
  input.codePoints().forEach(cp -> symbolCounts.computeIfAbsent(cp, key -> new LongAdder()).increment());
 }

 /**
  * Retrieve statistics for a series of input character sequences.
  * Handles code points wider than 16 bits and uses multiple threads.
  * @param inputs character sequences to analyze.
  * @return a map of each code point to its statistics.
  */
 public Map<Integer, Long> retrieveStats(Collection<? extends CharSequence> inputs) {
  final Set<Thread> counters = new HashSet<>();

  symbolCounts.clear();
  for (CharSequence input : inputs) {
   final Thread counter = new Thread(() -> updateStats(input));
   counters.add(counter);
   counter.start();
  }

  for (Thread counter : counters) {
   try {
    counter.join();
   }
   catch (InterruptedException exc) {
    final String FMT = "Awaiting %s: %s\n";
    System.err.printf(FMT, counter.getName(), exc.getMessage());
    Thread.currentThread().interrupt();
   }
  }

  final Map<Integer, Long> result = new HashMap<>();
  for (Map.Entry<Integer, LongAdder> entry : symbolCounts.entrySet()) {
   result.put(entry.getKey(), entry.getValue().longValue());
  }
  return result;
 }

 static List<List<CharSequence>> testData = List.of(
  List.of("One element in list only."),
  List.of("Many", "elements", "to", "analyze", "...…")
 );

 public static void main(String... args) {
  final String FMT = "Inputs:\n%s\nStats:\n%s\n\n";

  for (List<CharSequence> scenario : testData) {
   try (SymbolCounter counter = new SymbolCounter()) {
    var counts = counter.retrieveStats(scenario);
    System.out.printf(FMT, scenario, counts);
   }
  }
 }
}
