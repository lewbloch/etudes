/* Copyright 2024 Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.accession;

public interface AccessUnspecified {
    String FMT = "Type %s: %s\n\n";

    interface Announcing {
        void announce();
    }
}
