/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.collexion;

import java.util.ArrayList;
import java.util.List;

public class Intersectory {
    record Foo(String value) {}

    public static void main(String... args) {
        Foo[] firstArray = {
            new Foo(""), new Foo("foo"), new Foo("foo"), new Foo("bar"),
            new Foo("baz"), new Foo("qux"), new Foo("foo"), new Foo("orx"),
        };
        Foo[] secondArray = {
            new Foo(""), new Foo("foo"),
        };
        final Foo[] empty = {};

        List<Foo> first = new ArrayList<>(List.of(firstArray));
        List<Foo> second = List.of(secondArray);
        first.retainAll(second);

        System.out.print("{ ");
        Foo[] intersected = first.toArray(empty);
        for (Foo foo : intersected) {
            System.out.printf("%s, ", foo);
        }
        System.out.println("}");
    }
}

