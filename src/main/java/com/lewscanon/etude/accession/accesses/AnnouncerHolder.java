/* Copyright 2024-2025 Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.accession.accesses;

import static com.lewscanon.etude.accession.AccessUnspecified.Announcing;
import static com.lewscanon.etude.accession.AccessUnspecified.FMT;

public class AnnouncerHolder {
    static class Announcer implements Announcing {
        @Override
        public void announce() {
            System.out.printf(FMT, getClass(),
                "implements " + Announcing.class.getName());
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
