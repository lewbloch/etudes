package com.lewscanon.etude.cloader;

class A {
    static {
        System.out.println("initialize A");
    }

    static String show() {
        return "show " + A.class.getSimpleName();
    }
}

class B extends A {
    static {
        System.out.println("initialize B");
    }

    static String show() {
        return "show " + B.class.getSimpleName();
    }
}

public class LadenInit {
    public static void main(String[] args) throws Exception {
        Class<A> clazzA = A.class;
        Class<B> clazzB = B.class;
        System.out.println(clazzB.getSimpleName());
        System.out.println(clazzA.getSimpleName());

        System.out.println(B.show());
        System.out.println(A.show());
    }
}
