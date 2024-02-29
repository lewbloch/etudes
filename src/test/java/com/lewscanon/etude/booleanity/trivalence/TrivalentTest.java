/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.booleanity.trivalence;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class TrivalentTest {
    static final String VALID = "%s observed, %s expected%n";

    public record BiOp(Trivalent trivand, Trivalent augend, Trivalent expected) {}
    public record UnOp(Trivalent operand, Trivalent expected) {}

    @Test(dataProvider = "notCases")
    public void testNot(UnOp scenario) {
        final Trivalent observed = scenario.operand.not();
        final Trivalent expected = scenario.expected;
        final String debug = String.format(VALID, observed, expected);
        assertEquals(observed, expected, debug);
    }

    @Test(dataProvider = "andCases")
    public void testAnd(BiOp scenario) {
        final Trivalent observed = scenario.trivand.and(scenario.augend);
        final Trivalent expected = scenario.expected;
        final String debug = String.format(VALID, observed, expected);
        assertEquals(observed, expected, debug);
    }

   @Test(dataProvider = "eqvCases")
   public void testEqv(BiOp scenario) {
       final Trivalent observed = scenario.trivand.eqv(scenario.augend);
       final Trivalent expected = scenario.expected;
       final String debug = String.format(VALID, observed, expected);
       assertEquals(observed, expected, debug);
    }

    @Test(dataProvider = "orrCases")
    public void testOrr(BiOp scenario) {
        final Trivalent observed = scenario.trivand.orr(scenario.augend);
        final Trivalent expected = scenario.expected;
        final String debug = String.format(VALID, observed, expected);
        assertEquals(observed, expected, debug);
    }

    @Test(dataProvider = "xorCases")
    public void testXor(BiOp scenario) {
        final Trivalent observed = scenario.trivand.xor(scenario.augend);
        final Trivalent expected = scenario.expected;
        final String debug = String.format(VALID, observed, expected);
        assertEquals(observed, expected, debug);
    }

    @DataProvider(name = "notCases")
    public Iterator<UnOp> notProvider() {
        return List.of(new UnOp(Trivalent.FALSE, Trivalent.TRUE),
            new UnOp(Trivalent.UNKNOWN, Trivalent.UNKNOWN),
            new UnOp(Trivalent.TRUE, Trivalent.FALSE))
            .iterator();
    }

    @DataProvider(name = "andCases")
    public Iterator<BiOp> andProvider() {
        return List.of(
            new BiOp(Trivalent.FALSE, Trivalent.FALSE, Trivalent.FALSE),
            new BiOp(Trivalent.FALSE, Trivalent.UNKNOWN, Trivalent.UNKNOWN),
            new BiOp(Trivalent.FALSE, Trivalent.TRUE, Trivalent.FALSE),
            new BiOp(Trivalent.UNKNOWN, Trivalent.FALSE, Trivalent.UNKNOWN),
            new BiOp(Trivalent.UNKNOWN, Trivalent.UNKNOWN, Trivalent.UNKNOWN),
            new BiOp(Trivalent.UNKNOWN, Trivalent.TRUE, Trivalent.UNKNOWN),
            new BiOp(Trivalent.TRUE, Trivalent.FALSE, Trivalent.FALSE),
            new BiOp(Trivalent.TRUE, Trivalent.UNKNOWN, Trivalent.UNKNOWN),
            new BiOp(Trivalent.TRUE, Trivalent.TRUE, Trivalent.TRUE))
            .iterator();
    }

    @DataProvider(name = "eqvCases")
    public Iterator<BiOp> eqvProvider() {
        return List.of(
            new BiOp(Trivalent.FALSE, Trivalent.FALSE, Trivalent.TRUE),
            new BiOp(Trivalent.FALSE, Trivalent.UNKNOWN, Trivalent.UNKNOWN),
            new BiOp(Trivalent.FALSE, Trivalent.TRUE, Trivalent.FALSE),
            new BiOp(Trivalent.UNKNOWN, Trivalent.FALSE, Trivalent.UNKNOWN),
            new BiOp(Trivalent.UNKNOWN, Trivalent.UNKNOWN, Trivalent.UNKNOWN),
            new BiOp(Trivalent.UNKNOWN, Trivalent.TRUE, Trivalent.UNKNOWN),
            new BiOp(Trivalent.TRUE, Trivalent.FALSE, Trivalent.FALSE),
            new BiOp(Trivalent.TRUE, Trivalent.UNKNOWN, Trivalent.UNKNOWN),
            new BiOp(Trivalent.TRUE, Trivalent.TRUE, Trivalent.TRUE))
            .iterator();
    }

    @DataProvider(name = "orrCases")
    public Iterator<BiOp> orrProvider() {
        return List.of(
            new BiOp(Trivalent.FALSE, Trivalent.FALSE, Trivalent.FALSE),
            new BiOp(Trivalent.FALSE, Trivalent.UNKNOWN, Trivalent.UNKNOWN),
            new BiOp(Trivalent.FALSE, Trivalent.TRUE, Trivalent.TRUE),
            new BiOp(Trivalent.UNKNOWN, Trivalent.FALSE, Trivalent.UNKNOWN),
            new BiOp(Trivalent.UNKNOWN, Trivalent.UNKNOWN, Trivalent.UNKNOWN),
            new BiOp(Trivalent.UNKNOWN, Trivalent.TRUE, Trivalent.UNKNOWN),
            new BiOp(Trivalent.TRUE, Trivalent.FALSE, Trivalent.TRUE),
            new BiOp(Trivalent.TRUE, Trivalent.UNKNOWN, Trivalent.UNKNOWN),
            new BiOp(Trivalent.TRUE, Trivalent.TRUE, Trivalent.TRUE))
            .iterator();
    }

    @DataProvider(name = "xorCases")
    public Iterator<BiOp> xorProvider() {
        return List.of(
            new BiOp(Trivalent.FALSE, Trivalent.FALSE, Trivalent.FALSE),
            new BiOp(Trivalent.FALSE, Trivalent.UNKNOWN, Trivalent.UNKNOWN),
            new BiOp(Trivalent.FALSE, Trivalent.TRUE, Trivalent.TRUE),
            new BiOp(Trivalent.UNKNOWN, Trivalent.FALSE, Trivalent.UNKNOWN),
            new BiOp(Trivalent.UNKNOWN, Trivalent.UNKNOWN, Trivalent.UNKNOWN),
            new BiOp(Trivalent.UNKNOWN, Trivalent.TRUE, Trivalent.UNKNOWN),
            new BiOp(Trivalent.TRUE, Trivalent.FALSE, Trivalent.TRUE),
            new BiOp(Trivalent.TRUE, Trivalent.UNKNOWN, Trivalent.UNKNOWN),
            new BiOp(Trivalent.TRUE, Trivalent.TRUE, Trivalent.FALSE))
            .iterator();
    }
}
