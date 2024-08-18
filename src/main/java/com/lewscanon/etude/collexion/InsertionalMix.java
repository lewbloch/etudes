/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.collexion;

import java.util.LinkedHashSet;

public class InsertionalMix {
    public static void main(String[] args) {
        LinkedHashSet<String> insertional = new LinkedHashSet<>();
        insertional.add("First");
        insertional.addFirst("Second");
        insertional.add("Third");
        insertional.addFirst("Fourth");
        System.out.printf("Set: %s\n", insertional);
    }
}
