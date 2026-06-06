package com.lewscanon.etude.concurrency;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

/** Proof that java.util.Vector is not thread safe. */
public class UnsafeListing {
    static final long SLEEPFOR = 66L;

    private final List<String> listings = new Vector<>(); // bad!

    public boolean insert(String key) {
        if (!listings.contains(key)) {
            safeSleep((long) (SLEEPFOR * Math.random()));
            return listings.add(key);
        }
        else {
            return false;
        }
    }

    public List<String> getListings() {
        return Collections.unmodifiableList(listings);
    }

    public static void main(final String... args) {
        final String[] data = args.length == 0 ? new String[] {"test", "one", "two", "three"} : args;

        final UnsafeListing listing = new UnsafeListing();

        final class Runner implements Runnable {
            @Override
            public void run() {
                final String threadName = Thread.currentThread().getName();

                for (final String entry : data) {
                    if (! listing.insert(entry)) {
                        System.err.printf("%s: \"%s\" not inserted%n", threadName, entry);
                    }
                    else {
                        System.err.printf("%s: \"%s\" inserted%n", threadName, entry);
                    }
                }
            }
        }

        final Thread runner1 = new Thread(new Runner());
        final Thread runner2 = new Thread(new Runner());

        runner1.start();
        runner2.start();

        safeJoin(runner1);
        safeJoin(runner2);

        System.out.printf("Associations: %s%n", listing.getListings());
    }

    static void safeJoin(final Thread toJoin) {
        try {
            toJoin.join();
        }
        catch (InterruptedException exc) {
            System.out.printf("Interrupted join%n");
            Thread.currentThread().interrupt();
        }
    }

    static void safeSleep(final long millis) {
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException exc) {
            System.out.printf("Interrupted sleep%n");
            Thread.currentThread().interrupt();
        }
    }
}
