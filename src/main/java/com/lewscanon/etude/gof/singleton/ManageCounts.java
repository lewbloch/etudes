/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.gof.singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ManageCounts {
    static final List<List<String>> testData = List.of(
        List.of("fancy", "stands", "Zanzibar", "trance", "blued"),
        List.of("06lkds8", "SG&*(jhSTG*{PW${^T&")
    );

    public static void main(String... args) {
        final List<Map<Integer, Long>> results = Collections.synchronizedList(new ArrayList<>());

        final Set<Thread> threads = new HashSet<>();
        for (List<String> data : testData) {
            threads.add(new Thread(() -> results.add(CounterManager.STATS.apply(data))));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            }
            catch (InterruptedException exc) {
                System.err.printf("%s\n", exc.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        System.out.printf("\n%s\n", results);
    }
}
