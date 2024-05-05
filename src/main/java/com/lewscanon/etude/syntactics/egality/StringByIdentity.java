/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.syntactics.egality;

@SuppressWarnings({"StringOperationCanBeSimplified", "StringEquality"})
public class StringByIdentity {
    static final String CONTRAST = "%s: equals? %b,  ==? %b\n";

    public void contrast(final String scenario, final String left, final String right) {
        System.out.printf(CONTRAST, scenario, left.equals(right), left == right);
    }

    public static void main(String[] args) {
        final StringByIdentity driver = new StringByIdentity();

        final String test = "foo";

        final String interned = new String(test).intern();
        driver.contrast("original val to intern", test, interned);

        final String unintern = new String(interned);
        driver.contrast("uninterned to interned", unintern, interned);

        final String reintern = unintern.intern();
        driver.contrast("reinterned to interned", reintern, interned);
        driver.contrast("reinterned to unintern", reintern, unintern);
    }
}
