/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.puzzle;

import java.util.HashMap;
import java.util.Map;

/**
 * 133tc0d3 567: Permutation in String
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * In other words, return true if one of s1's permutations is the substring of s2.
*/
public class PermutationChecker {
    /**
     * Determine whether any permutation of a string is contained within another string.
     * @param s1 the string whose permutations to check.
     * @param s2 the string whose containment of a permutation is checked.
     * @return true if and only if {@code s2} contains a permutation of {@code s1}.
     */
    public boolean containsPermutation(String s1, String s2) {
        if (s1 == null || s2 == null
                || s1.isEmpty() || s2.isEmpty()
                || s1.length() > s2.length()) {
            return false;
        }

        final Map<Integer, Integer> permutes = new HashMap<>();
        s1.codePoints().forEach(point -> permutes.put(point, permutes.getOrDefault(point, 0) + 1));

        final Map<Integer, Integer> contains = new HashMap<>();
        s2.codePoints().forEach(point -> contains.put(point, contains.getOrDefault(point, 0) + 1));

        for (var permute : permutes.entrySet()) {
            if (permute.getValue() > contains.getOrDefault(permute.getKey(), 0)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Test the method.
     * @param args arguments.
     */
    public static void main(String... args) {
        final String RESOUT = "Is a permutation of \"%s\" in \"%s\"? %b%n";

        final String[][] testData = {
            {null, "San Simeone"},
            {"San Simeone", null},
            {"", "San Simeone"},
            {"San Simeone", ""},
            {"one", "San Simeone"},
            {"San Simeone", "one"},
            {"Same neon", "San Simeone"},
            {"San Simeone", "Same neon"},
            {"Same neonSi", "San Simeone"},
            {"San Simeone", "Same neonSi"},
        };

        final PermutationChecker perm = new PermutationChecker();
        for (final String[] data : testData) {
            System.out.printf(RESOUT, data[0], data[1], perm.containsPermutation(data[0], data[1]));
        }
    }
}
