/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.sealing;

public class Allowed {
    public static non-sealed class Permitted extends SealYa {
    }

    public static sealed class Entitled extends SealYa {
    }

    public static final class Authorized extends Entitled {
    }

    public static void main(String... args) {
        SealYa sealYa = new Permitted();
        sealYa.announce();

        sealYa = new Entitled();
        sealYa.announce();

        sealYa = new Authorized();
        sealYa.announce();
    }
}
