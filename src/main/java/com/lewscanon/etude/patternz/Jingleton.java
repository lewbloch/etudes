/* Copyright © 2025 Lewis S. Bloch */
package com.lewscanon.etude.patternz;

import java.time.LocalTime;

public enum Jingleton {
    JINGLE("jingle");

    private static final String JFORMAT = " Jingleton time = %s\t%s\n";
    static {
        System.out.printf(JFORMAT, LocalTime.now(), "static initializer");
        try {Thread.sleep(1500);} catch (InterruptedException ignore) {}
    }

    public static void showValues() {
        System.out.printf(JFORMAT, LocalTime.now(), "showValues()");
        for (Jingleton jangle : values()) {
            System.out.printf("%s\n", jangle);
        }
        try {Thread.sleep(1500);} catch (InterruptedException ignore) {}
    }

    private final String name;

    private String resource;

    Jingleton(String name) {
        System.out.printf(JFORMAT, LocalTime.now(), "constructor");
        this.name = name;
        try {Thread.sleep(1500);} catch (InterruptedException ignore) {}
    }

    @Override public String toString() {
        return " Jingleton: { name: \"" + getName()
               + "\", resource: \"" + getResource()
               + "\" }";
    }

    public String getName() {
        return name;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
