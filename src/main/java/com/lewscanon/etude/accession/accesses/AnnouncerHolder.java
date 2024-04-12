/* Copyright 2024 Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.accession.accesses;

import static com.lewscanon.etude.accession.AccessUnspecified.Announcing;

public class AnnouncerHolder {
    static class Announcer implements Announcing {
        @Override
        public void announce() {
            System.out.printf("%s\n implements \n%s\n",
                getClass(), Announcing.class);
        }
    }

    public static void main(String... args) {
        final Announcer announcer = new Announcer();
        announcer.announce();
    }
}
