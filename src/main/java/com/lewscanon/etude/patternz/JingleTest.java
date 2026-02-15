/* Copyright © 2025 Lewis S. Bloch */
package com.lewscanon.etude.patternz;

import java.time.LocalTime;

public class JingleTest {
    private static final String TFORMAT = "\nJingleTest time = %s\t%s\n";

    public static void main(String[] args) {
        System.out.printf(TFORMAT, LocalTime.now(),
                "Jingleton.class.getSimpleName()");
        System.out.printf(" Jingleton class = %s\n",
                Jingleton.class.getSimpleName());

        System.out.printf(TFORMAT, LocalTime.now(),
                "Thread.sleep(1500)");
        try {Thread.sleep(1500);} catch (InterruptedException ignore) {}

        System.out.printf(TFORMAT, LocalTime.now(),
                "Jingleton.showValues()");
        Jingleton.showValues();

        System.out.printf(TFORMAT, LocalTime.now(),
                "Jingleton.JINGLE.setResource()");
        Jingleton.JINGLE.setResource("jangle");

        System.out.printf(TFORMAT, LocalTime.now(),
                "Jingleton.showValues()");
        Jingleton.showValues();
    }
}
