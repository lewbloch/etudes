/*
 * Copyright © 2024, Lewis S. Bloch. All rights reserved.
 */

package com.lewscanon.etude.lesnids;

public class OuterInner {
    private int x = 10;

    class Inner {
        private int x = 20;

        public void print() {
            System.out.println(x);
        }
    }

    public static void main(String[] args) {
        OuterInner outer = new OuterInner();
        OuterInner.Inner inner = outer.new Inner();
        inner.print();
    }
}
