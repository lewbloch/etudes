package com.lewscanon.etude.flowcontrol;

import java.util.Date;
import java.util.Random;

public class Frando {
    static final double STOP_ODDS = 0.057;
    static final Random randy = new Random(new Date().getTime());

    static boolean keepGoing() {
        return randy.nextDouble() >= STOP_ODDS;
    }

    public static void main(String... args) {
        final int NLOOPS = 11;
        final String BEGIN = "Begin";
        final String STOPPING = "\n%d iterations\n";

        System.out.println(BEGIN);
        for (int loops = 0; loops < NLOOPS; ++loops) {
            int kount = 0;
            for (boolean going = keepGoing();
                 going;
                 ++kount, going = keepGoing()) {
                System.out.print('.');
            }
            System.out.printf(STOPPING, kount);
        }
    }
}
