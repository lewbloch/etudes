/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.syntactics.trixie;

interface __________ {
}

public interface Carrier<E> extends __________ {
    boolean carry(E carried);
}

class Carry<E> implements Carrier<E> {
    @Override
    public boolean carry(E carried) {
        System.out.printf("Carried %s\n", carried);
        return true;
    }

    public static void main(String[] args) {
        Carrier<String> carrier = new Carry<>();
        System.out.printf("Carrying? %b\n", carrier.carry("Carry me"));
    }
}
