// Copyright © 2023 Lewis S. Bloch. All rights reserved.
package com.lewscanon.etude.algorithm;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** Fibonacci implementation, memoized, thread safe. */
public class Fibonacho {
    static final String LARGEF = "%d too large, must be <= %d%n";
    static final String OUTF = "fib(%d) = %d%n";
    static final String RECURF = "%d recursions %n";

    static final Map<Character, BigInteger> memoriam = new HashMap<>();
    static final ReadWriteLock lock = new ReentrantReadWriteLock();
    static final AtomicInteger recursions = new AtomicInteger(0);

    /**
     * Retrieve the memoized result of {@code enn -> f(enn)}.
     * @param enn the value to
     * @return the memoized result, {@code null} if not found.
     */
    static BigInteger getMemoriam(char enn) {
        lock.readLock().lock();
        try {
            return memoriam.get(enn);
        }
        finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Memoize a value and its result.
     * @param enn the value to memoize.
     * @param result the result.
     */
    static BigInteger putMemoriam(char enn, BigInteger result) {
        lock.writeLock().lock();
        try {
            memoriam.putIfAbsent(enn, result);
            return result;
        }
        finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Calculate the {@code n}th Fibonacci number, starting with {@code fib(0) -> 0, fib(1) -> 1}.
     * @param enn nonnegative integral operand of Fibonacci function.
     * @return the {@code enn}th Fibonacci number.
     */
    public BigInteger fib(final char enn) {
        return Optional.ofNullable(getMemoriam(enn))
                .orElseGet(() -> putMemoriam(enn, fibonacci(enn)));
    }

    /**
     * Calculate the {@code n}th Fibonacci number, starting with {@code fib(0) -> 0, fib(1) -> 1}.
     * @param enn nonnegative integral operand of Fibonacci function.
     * @return the {@code enn}th Fibonacci number.
     */
    BigInteger fibonacci(final char enn) {
        return switch (enn) {
            case 0 -> BigInteger.ZERO;
            case 1 -> BigInteger.ONE;
            default -> {
                recursions.incrementAndGet();
                yield fib((char) (enn - 1)).add(fib((char) (enn - 2)));
            }
        };
    }

    /**
     * Exercise the code.
     * @param args arguments.
     */
    public static void main(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Calculate Fibonacci number of ? ");
            for (var userChoice = scanner.nextInt(); userChoice >= 0; userChoice = scanner.nextInt()) {
                recursions.set(0);
                if (userChoice > Character.MAX_VALUE) {
                    System.out.printf(LARGEF, userChoice, (int) Character.MAX_VALUE);
                }
                else {
                    Fibonacho fibber = new Fibonacho();
                    System.out.printf(OUTF, userChoice, fibber.fib((char) userChoice));
                    System.out.printf(RECURF, recursions.get());
                }
                System.out.printf("%nAnother? ");
            }
        }
    }
}
