/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.syntactics;

import java.util.Optional;

/**
 * Java initializations and invocations sequence weirdly.
 * <br>
 * Run this code, then figure out why the sequence happens as it does.
 * <br>
 * Or try to predict it, then run this code and figure out why you were wrong.
 */
public class Parentalism {
    @SuppressWarnings("AccessStaticViaInstance")
    public static void main(String... args) {
        Parental.showMyType();

        final Youngun youngun = new Youngun();
        youngun.showMyType();

        System.out.printf("%s class referenced\n", Child.class.getSimpleName());

        final Parent parent = new Child("example");
        System.out.printf("%s ", parent);
        parent.showMyType();

        System.out.print("null reference ");
        final Parent nope = null;
        nope.showMyType();
    }
}

interface Parental {
    static void showMyType() {
        System.out.printf("%s shown\n", Parental.class.getSimpleName());
    }
}

class Youngun implements Parental {
    static {
        System.out.printf("%s initialized\n", Youngun.class.getSimpleName());
    }

    static void showMyType() {
        System.out.printf("%s shown, calls ", Youngun.class.getSimpleName());
        Parental.showMyType();
    }

    public Youngun() {
        System.out.printf("%s constructed\n", Youngun.class.getSimpleName());
    }
}

class Parent implements Parental {
    static {
        System.out.printf("%s initialized\n", Parent.class.getSimpleName());
    }

    static void showMyType() {
        System.out.printf("%s shown\n", Parent.class.getSimpleName());
    }
}

class Child extends Parent {
    static {
        System.out.printf("%s initialized\n", Child.class.getSimpleName());
    }
    static void showMyType() {
        System.out.printf("%s shown\n", Child.class.getSimpleName());
    }

    final String name;
    final String represen;

    public Child(String name) {
        this.name = Optional.of(name).orElse("");
        this.represen = String.format("Child(\"%s\")", this.name);
        System.out.printf("%s constructed\n", this);
    }

    @Override
    public final String toString() {
        return represen;
    }
}
