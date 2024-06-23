/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Objects.requireNonNull;

/**
 *
 */
public record ConcurrentBuilder(String input) {
    static final String FMT = "%s\n%s\n\n";
    static final String INJECTED = " ";
    static final int NUMTRIALS = 6;

    static final List<String> testData = List.of(
        "Now it is a time for all good humans to come to the aid of some other humans."
    );


    /**
     * Instance owns input.
     * @param input {@link String} to reassemble.
     */
    public ConcurrentBuilder(final String input) {
        this.input = Optional.of(input).orElse("");
    }

    /**
     * Buffer and reassemble the input.
     *
     * @return the reassambled {@link String}.
     */
    public String reassemble(final List<String> buffer) {
        assert input != null;
        requireNonNull(buffer, "null buffer").clear();

        final List<Thread> pool = new ArrayList<>();
        for (var piece : input.split(INJECTED)) {
            pool.add(new Thread(() -> {
                buffer.add(piece);
                buffer.add(INJECTED);
            }));
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

        final StringBuilder assembler = new StringBuilder();
        buffer.forEach(assembler::append);
        return assembler.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (ConcurrentBuilder) obj;
        return Objects.equals(this.input, that.input);
    }

    @Override
    public int hashCode() {
        return Objects.hash(input);
    }

    @Override
    public String toString() {
        return "ConcurrentBuilder(" + input + ')';
    }

    /**
     * Try it.
     * @param args arguments.
     */
    public static void main(String... args) {
        for (var data : testData) {
            for (int trial = 0; trial < NUMTRIALS; ++trial) {
                final ConcurrentBuilder builder = new ConcurrentBuilder(data);
                System.out.printf(FMT, builder.input(),
                        builder.reassemble(new CopyOnWriteArrayList<>()));
            }
        }
    }
}
