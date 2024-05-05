/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.gof.singleton;

import com.lewscanon.etude.puzzle.SymbolCounter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

/** Control access to a {@link SymbolCounter}. */
public enum CounterManager
        implements Function<Collection<? extends CharSequence>, Map<Integer, Long>> {
    /** The {@link CounterManager}. */
    STATS;

    /**
     * Analyze a collection of inputs.
     * @param inputs the inputs to analyze.
     * @return statistics from the analysis.
     */
    @Override
    public final Map<Integer, Long> apply(Collection<? extends CharSequence> inputs) {
        if (inputs == null || inputs.isEmpty()) {
            return Collections.emptyMap();
        }

        try (final SymbolCounter counter = new SymbolCounter()) {
            @SuppressWarnings("UnnecessaryLocalVariable")
            final var counts = counter.retrieveStats(inputs);
            return counts;
        }
    }
}
