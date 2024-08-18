/*
 * Copyright © 2024, Lewis S. Bloch. All rights reserved.
 */
package com.lewscanon.etude.functionary;

@SuppressWarnings("unused")
@FunctionalInterface
public interface GotEqval {
    int sum(int var1, int var2);
    boolean equals(Object obj);
}
