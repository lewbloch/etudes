/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.collexion;

import java.util.Arrays;
import java.util.TreeSet;

public class HoldsNull {
    static final String[] data =
        {"Foo", "Bar", null, "Baz", "Zab", "Baz", "Coz"};

    public static void main(String... args) {
        TreeSet<String> strung = new TreeSet<>((left, right) ->
            left == null? (right == null? 0 : -1)
                : right == null? 1 : left.compareTo(right));

        strung.addAll(Arrays.asList(data));
        System.out.printf("TreeSet contains:\n%s\n", strung);
    }
}
