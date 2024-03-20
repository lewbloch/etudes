/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.sealing;

public sealed class SealYa
        permits Allowed.Permitted, Allowed.Entitled {
    public void announce() {
        System.out.println(getClass().getSimpleName());
    }
}
