/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.booleanity;

import java.util.Map;

/** Boolean logic based on NAND. */
public enum Bool {
    FALSE(0), TRUE(1);

    private static final Map<Integer, Bool> truth =
        Map.of(0, TRUE, 1, TRUE, 2, FALSE);

    private final int bit;

    Bool(int bit) {
        this.bit = bit;
    }

    /**
     * {@code NAND} - {@code not AND}.
     * <br>
     * <code>
     *  p   q  p+q NAND
     * -------------------
     *  0   0   0   1
     *  0   1   1   1
     *  1   0   1   1
     *  1   1   2   0
     * </code>
     *
     * @param q operand.
     * @return result of operation.
     */
    public Bool nand(Bool q) {
        return q == null ? TRUE : truth.get(bit + q.bit);
    }

    /**
     * AND.
     * {@code p AND q == ((p NAND q) NAND TRUE)}.
     *
     * @param q operand.
     * @return result of operation.
     */
    public Bool and(Bool q) {
        return nand(q).nand(TRUE);
    }

    /**
     * OR.
     * {@code p OR q  == ((p NAND TRUE) NAND (q NAND TRUE))}.
     *
     * @param q operand.
     * @return result of operation.
     */
    public Bool or(Bool q) {
        return nand(TRUE).nand(q.nand(TRUE));
    }

    /**
     * ERGO.
     * {@code p ERGO q  == (p NAND (q NAND TRUE))}.
     *
     * @param q operand.
     * @return result of operation.
     */
    public Bool ergo(Bool q) {
        return nand(q.nand(TRUE));
    }

    /**
     * EQV.
     * {@code p EQV q  ==
     *  (p AND q) OR NOT(p OR q) ==
     *  (p NAND q NAND TRUE) NAND TRUE NAND (NOT(p OR q) NAND TRUE) ==
     *  (p NAND q NAND TRUE) NAND TRUE NAND ((NOT p AND NOT q) NAND TRUE) ==
     *  p NAND q NAND TRUE NAND TRUE NAND (((p NAND TRUE) AND (q NAND TRUE)) NAND TRUE) ==
     *  p NAND q NAND TRUE NAND TRUE NAND (((p NAND TRUE) NAND (q NAND TRUE) NAND TRUE) NAND TRUE) ==
     *  p NAND q NAND p NAND TRUE NAND q NAND
     *  TRUE
     * }.
     *
     * @param q operand.
     * @return result of operation.
     */
    public Bool eqv(Bool q) {
        return nand(TRUE).nand(q.nand(TRUE));
    }

    /**
     * NOT.
     * {@code (NOT p) == (p NAND TRUE)}.
     *
     * @return result of operation.
     */
    public Bool not() {
        return nand(TRUE);
    }

    /**
     * NOT.
     * {@code (ID p) == (p NAND FALSE)}.
     *
     * @return result of operation.
     */
    public Bool id() {
        return nand(FALSE);
    }

}
