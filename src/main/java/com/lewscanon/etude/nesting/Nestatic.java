/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.nesting;

public class Nestatic {
    public static class Foo {
        private static final String FMT = "{ label: \"%s\", value: %3d }";

        final String label;
        final int value;
        final String repres;

        public Foo(String label, int value) {
            this.label = label;
            this.value = value;
            this.repres = String.format(FMT, label, value);
        }

        @Override
        public String toString() {
            return repres;
        }
    }

    public static void main(String... args) {
        Foo frou = new Foo("Flew", 2);
        Foo frei = new Foo("Flyer", 17);
        System.out.printf("frou = %s \nfrei = %s", frou, frei);
    }
}
