/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.gof.observer;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;
import static java.lang.Thread.currentThread;
import static java.time.LocalTime.now;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

/** Exercise the observer system. */
public record Audience(String name) implements Runnable {
    static final String EVENT_FMT = "%s [%s] EVENT %d";
    static final String INTERRUPTED_FMT = "%s: interrupted%n";
    static final String NAME_FMT = ".%d";
    static final int NUM_THREADS = 4;
    static final int REPETITIONS = 7;
    static final long END_DELAY = 500L;
    static final long THREAD_DELAY = 60L;

    static final Observed observed = new Observed();
    static final Random rando = new Random(new Date().getTime());

    static final AtomicInteger eventCount = new AtomicInteger(0);

    /**
     * Safe {@code sleep}.
     * @param millis The number of milliseconds to sleep.
     */
    static void hardSleep(long millis) {
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException exc) {
            System.out.printf(INTERRUPTED_FMT, currentThread().getName());
            currentThread().interrupt();
        }
    }

    /**
     * Randomized safe {@code sleep}.
     * @param millis The maximum number of milliseconds to sleep.
     */
    static void randomSleep(long millis) {
        final long MIN_SLEEP = 500L;
        hardSleep(MIN_SLEEP + rando.nextLong(millis));
    }

    /**
     * Constructor.
     * @param name this {@code Audience} name or the default name if {@code null} or empty.
     */
    public Audience(String name) {
        this.name = name == null || name.isEmpty() ? "DEFAULT" : name;
    }

    /**
     * Get the name.
     * @return the name.
     */
    @Override
    public String name() {
        return name;
    }

    @Override
    public void run() {
        try (@SuppressWarnings("unused")    // quiet the linter
             final Observant observant = new Observant(observed)) {

            for (int count = 0; count < REPETITIONS; ++count) {
                observed.alert(makeEvent());
                randomSleep(THREAD_DELAY);
            }
        }
        hardSleep(END_DELAY);
    }

    /**
     * Make a {@link String} event.
     * @return an {@link Actor.Event}.
     */
    public Actor.Event<String> makeEvent() {
        final String payload = format(EVENT_FMT, currentThread().getName(),
                now().format(ISO_LOCAL_TIME),
                eventCount.getAndIncrement());
        return new Actor.Event<>(payload);
    }

    /**
     * Drive the system.
     * @param args arguments.
     */
    public static void main(String... args) {
        final Audience[] audiences;
        if (args.length == 0) {
            audiences = new Audience[NUM_THREADS];
            for (int ax = 0; ax < NUM_THREADS; ++ax) {
                audiences[ax] = new Audience(format(NAME_FMT, ax));
            }
        }
        else {
            audiences = new Audience[args.length];
            int ax = 0;
            for (String arg : args) {
                audiences[ax++] = new Audience(arg);
            }
        }

        final Thread[] threads = new Thread[audiences.length];

        for (int ax = 0; ax < audiences.length; ++ax) {
            threads[ax] = new Thread(audiences[ax], audiences[ax].name());
            threads[ax].start();
            randomSleep(100L);
        }

        hardSleep(2_000L);

        for (Thread thread : threads) {
            try {
                thread.join();
            }
            catch (InterruptedException exc) {
                System.out.printf("%n" + INTERRUPTED_FMT + "%s%n",
                        currentThread().getName(), thread.getName());
                currentThread().interrupt();
            }
        }
    }
}
