/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.interfacade;

public interface Modifesty {
    static String TYPE_FMT = "Type: %s\n";

    public static void announce() {
        System.out.printf(TYPE_FMT, Modifesty.class.getSimpleName());
    }

    public abstract void developAbs();

    public default void showMe() {
        identify();
    }

    private void identify() {
        System.out.printf(TYPE_FMT, getClass().getName());
    }

    public static void main(String... args) {
        Modifesty modifesty = new Modifesty(){
            @Override
            public void developAbs() {
                showMe();
            }
        };
        Modifesty.announce();
        modifesty.developAbs();
    }
}
