package com.lewscanon.etude;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
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
     * Calculate the {@code n}th Fibonacci number, starting with {@code fib(0) -> 0, fib(1) -> 1}.
     * @param enn nonnegative integral operand of Fibonacci function.
     * @return the {@code enn}th Fibonacci number.
     */
    public BigInteger fib(char enn) {
        lock.readLock().lock();
        final BigInteger value = memoriam.get(enn);
        lock.readLock().unlock();

        if (value != null) {
            return value;
        }
        else {
            final BigInteger result = switch (enn) {
                case 0 -> BigInteger.ZERO;
                case 1 -> BigInteger.ONE;
                default -> {
                    recursions.incrementAndGet();
                    yield fib((char) (enn - 1)).add(fib((char) (enn - 2)));
                }
            };

            lock.writeLock().lock();
            memoriam.putIfAbsent(enn, result);
            lock.writeLock().unlock();

            return result;
        }
    }

    /**
     * Fibonacci program to test the {@code fib()} function.
     * @param args arguments.
     */
    public static void main(String[] args) {
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
