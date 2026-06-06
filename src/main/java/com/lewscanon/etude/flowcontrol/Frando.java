package com.lewscanon.etude.flowcontrol;

import java.util.Date;
import java.util.Random;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Frando {
    static final String BEGIN = "Begin";
    static final String INTERRUPTED = "\nInterrupted";
    static final String STOPPED = "\n%d iterations\n";
    static final long INTERV = 301L;
    static final int NLOOPS = 7;

    static final double ODDS = 0.061;
    static final Random RANDY = new Random(new Date().getTime());

    static boolean keepGoing() {
        return RANDY.nextDouble() >= ODDS;
    }

    public static void main(String... args) {
        System.out.println(BEGIN);
        for (int loops = 0; loops < NLOOPS; ++loops) {
            int kount = 0;
            try {
                for (char sep = '.'; keepGoing(); sleep(INTERV), ++kount) {
                    System.out.print(sep);
                }
            }
            catch (InterruptedException exc) {
                System.err.println(INTERRUPTED);
                currentThread().interrupt();
            }
            System.out.printf(STOPPED, kount);
        }
    }
}
