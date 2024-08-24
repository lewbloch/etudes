/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.concurrency;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Objects.requireNonNull;

/* Show the non-concurrent-friendliness of chained methods. */
public record ConcurrentBuilder(String initial) {
    static final String FMT = "%s\n";
    static final String INJECTED = " ";
    static final int NUMTRIALS = 6;

    static final List<String> testData = List.of(
        "Now it is a time for all good humans to come to the aid of some other humans."
    );

    /**
     * Canonical constructor.
     * @param initial {@link String} to reassemble.
     */
    public ConcurrentBuilder(final String initial) {
        this.initial = Optional.of(initial).orElse("");
    }

    /**
     * Reassemble the initial value into a provided buffer.
     * This will show the effect of different implementations.
     * @param buffer the buffer into which to reassemble the value.
     * @return the reassembled value.
     */
    public String reassemble(final List<String> buffer) {
        assert initial != null;
        requireNonNull(buffer, "null buffer").clear();

        final List<Thread> pool = new ArrayList<>();

        // Each thread receives one of the pieces to reassemble
        // Even if {@code buffer.add} is synchronized there could be trouble.
        for (final var piece : initial.split(INJECTED)) {
            pool.add(new Thread(() -> {
                buffer.add(piece);
                buffer.add(INJECTED);
            }));
        }

        for (var thread : pool) {
            thread.start();     // add one piece to the reassembled target
        }
        for (var thread : pool) {
            try {
                thread.join();
            }
            catch (InterruptedException exc) {
                Thread.currentThread().interrupt(); // keep going
            }
        }

        final StringBuilder assembler = new StringBuilder();
        buffer.forEach(assembler::append);  // add buffer items to assembler in the order the threads buffered them
        return assembler.toString();
    }

    static void tryout(final List<String> buffer) {
        for (var data : testData) {
            final ConcurrentBuilder builder = new ConcurrentBuilder(data);
            System.out.printf(FMT, builder);

            for (int trial = 0; trial < NUMTRIALS; ++trial) {
                System.out.printf(FMT, builder.reassemble(buffer));
            }
        }
    }

    /**
     * Try it.
     * @param args arguments.
     */
    public static void main(String... args) {
        final String TRYF = "\n%s\n";

        System.out.printf(TRYF, "CopyOnWriteArrayList");
        tryout(new CopyOnWriteArrayList<>());

        System.out.printf(TRYF, "Collections.synchronizedList");
        tryout(Collections.synchronizedList(new ArrayList<>()));
    }
}
