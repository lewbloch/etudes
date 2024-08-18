/*
 * Copyright © 2024, Lewis S. Bloch. All rights reserved.
 */

package com.lewscanon.etude.syntactics.trixie;

public class FooLishness {
    public static void useThis() {
        class Foo {
            public String name;
            public String getName() { return this.name;}
        }
        Foo foo = new Foo();
        foo.name = "Lew";
        System.out.println(foo.getName());
    }

    public static void main(String... args) {
        useThis();
    }
}
