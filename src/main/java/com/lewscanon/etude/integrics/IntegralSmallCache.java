package com.lewscanon.etude.integrics;

/**
 * Behavior variations due to {@code -Djava.lang.Integer.IntegerCache.high}.
 */
public class IntegralSmallCache {
    public static void main(String[] args) {
        Integer autoTen = 10;
        Integer freshTen = new Integer (10);
        Integer valuedTen = Integer.valueOf(10);
        System.out.println("(autoTen == 10) " + (autoTen == 10));
        System.out.println("(autoTen == freshTen) " + (autoTen == freshTen));
        System.out.println("(autoTen == valuedTen) " + (autoTen == valuedTen));

        System.out.println();
        Integer autoThousand = 1000;
        Integer freshThousand = new Integer (1000);
        Integer valuedThousand = Integer.valueOf(1000);
        System.out.println("(autoThousand == 1000) " + (autoThousand == 1000));
        System.out.println("(autoThousand == freshThousand) " + (autoThousand == freshThousand));
        System.out.println("(autoThousand == valuedThousand) " + (autoThousand == valuedThousand));
    }
}
