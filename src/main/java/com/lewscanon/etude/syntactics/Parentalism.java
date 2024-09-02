/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.syntactics;

public class Parentalism {
    public static void main(String... args) {
        Parental.showMyType();
        Youngun.showMyType();

        Parent parent = null;
        parent.showMyType();

        parent = new Child();
        parent.showMyType();
    }
}

interface Parental {
    static void showMyType() {
        System.out.printf("%s\n", Parental.class.getSimpleName());
    }
}

class Youngun implements Parental {
    static void showMyType() {
        System.out.printf("%s\n", Youngun.class.getSimpleName());
    }
}

class Parent implements Parental {
    static void showMyType() {
        System.out.printf("%s\n", Parent.class.getSimpleName());
    }
}

class Child extends Parent {
    static void showMyType() {
        System.out.printf("%s\n", Child.class.getSimpleName());
    }
}
