/* Copyright © 2026 Lewis S. Bloch */
package com.lewscanon.etude.patternz;

import java.time.LocalTime;

public class LazyInit {
    static final String TFORM = " %s [%s]\t%s\n";

    static void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException exc) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String... args) {
        String myClazz = LazyInit.class.getSimpleName();

        String jfName = JustFinal.class.getSimpleName();
        System.out.printf(LazyInit.TFORM, myClazz, LocalTime.now(),
                jfName + " referenced");
        safeSleep(500);

        JustFinal justFinal = JustFinal.getInstance();
        safeSleep(500);
        System.out.printf(TFORM, myClazz, LocalTime.now(),
                "JustFinal instance " + justFinal);

        safeSleep(1000);
        System.out.println();

        String elName = ExtraLazy.class.getSimpleName();
        System.out.printf(TFORM, myClazz, LocalTime.now(),
                elName + " referenced");
        safeSleep(500);

        ExtraLazy extraLazy = ExtraLazy.getInstance();
        safeSleep(500);
        System.out.printf(TFORM, myClazz, LocalTime.now(),
                "ExtraLazy instance " + extraLazy);
    }
}

class JustFinal {
    static
    {
        System.out.printf(LazyInit.TFORM, JustFinal.class.getSimpleName(),
                LocalTime.now(), "static initialization");
        LazyInit.safeSleep(500);
    }

    private static final JustFinal JFIN = new JustFinal();
    static
    {
        System.out.printf(LazyInit.TFORM, JustFinal.class.getSimpleName(),
                LocalTime.now(), ": JFIN = " + JFIN);
        LazyInit.safeSleep(500);
    }

    public static JustFinal getInstance() {
        return JFIN;
    }

    private JustFinal() {
        System.out.printf(LazyInit.TFORM, JustFinal.class.getSimpleName(),
                LocalTime.now(), ": constructor");
        LazyInit.safeSleep(500);
    }

    @Override
    public String toString()
    {
        return "JFIN " + JFIN.hashCode();
    }
}

class ExtraLazy {
    static
    {
        System.out.printf(LazyInit.TFORM, ExtraLazy.class.getSimpleName(),
                LocalTime.now(), "static initialization");
        LazyInit.safeSleep(500);
    }

    private static volatile ExtraLazy LAZY;
    static
    {
        System.out.printf(LazyInit.TFORM, ExtraLazy.class.getSimpleName(),
                LocalTime.now(), ": LAZY = " + LAZY);
        LazyInit.safeSleep(500);
    }

    /** Double-checked locking. */
    public static ExtraLazy getInstance() {
        if (LAZY == null) {
            synchronized (ExtraLazy.class) {
                if (LAZY == null) {
                    LAZY = new ExtraLazy();
                }
            }
        }
        return LAZY;
    }

    private ExtraLazy() {
        System.out.printf(LazyInit.TFORM, ExtraLazy.class.getSimpleName(),
                LocalTime.now(), ": constructor");
        LazyInit.safeSleep(500);
    }

    @Override
    public String toString()
    {
        return "LAZY " + LAZY.hashCode();
    }
}
