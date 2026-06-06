package com.lewscanon.etude.charisma;

public class Funkych {
    private static final String FMT = "%s == %d == 0x%x\n";

    public static void main(String... args) {
        char three = '３';
        System.out.printf(FMT, "three - 0x30", three - 0x30, three - 0x30);
    }
}
