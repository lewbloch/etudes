/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.booleanity.trivalence;

/** Trivalent (3-valued) logic.
 * <br>
 * Referencing
 * https://homepage.cs.uiowa.edu/~dwjones/ternary/logic.shtml
 * Standard Ternary Logic
 * by Douglas W. Jones
 * THE UNIVERSITY OF IOWA Department of Computer Science
 * <br>
 * Monadic functions [Jones, op cit.]<br>
 * <code>
 *
 *  op | 0  1  2  3  4  5  6  7  8  9  A  B  C  D  E  F  G  H  K  M  N  P  R  T  V  X  Z
 *  ---+--------------------------------------------------------------------------------
 *   – | –  0  +  –  0  +  –  0  +  –  0  +  –  0  +  –  0  +  –  0  +  –  0  +  –  0  +
 *   0 | –  –  –  0  0  0  +  +  +  –  –  –  0  0  0  +  +  +  –  –  –  0  0  0  +  +  +
 *   + | –  –  –  –  –  –  –  –  –  0  0  0  0  0  0  0  0  0  +  +  +  +  +  +  +  +  +
 *
 * </code><br>
 * Only a few implemented here.<br>
 * <code>
 *
 *      NOT INC DEC PES OPT
 *  op |  5   7   B   K   V
 *  ---+---------------------
 *   – |  +   0   +   –   –
 *   0 |  0   +   –   –   +
 *   + |  –   –   0   +   +
 *
 * </code><br>
 */
public enum Trivalent {
    FALSE,
    UNKNOWN,
    TRUE
    ;

    /**
     * Obtain the constant from the secret {@code int} value.
     * @param value the secret value to find.
     * @return the corresponding constant.
     */
    static Trivalent fromInt(int value) {
        final int ord = value + 1;
        return (ord < 0 || ord >= values().length) ? null
                : values()[ord];
    }

    /**
     * Convert to the secret {@code int}.
     * @return the secret {@code int}.
     */
    int toInt() {
        return ordinal() - 1;
    }

    /**
     * {@code Trivalent} {@code NOT}.
     * @return the negation of this constant.
     */
    public Trivalent not() {
        return fromInt(-toInt());
    }

    /**
     * {@code Trivalent} {@code EQV} (equivalence).
     * @param other the other instance to compare.
     * @return the result of this {@code EQV} the argument.
     */
    public Trivalent eqv(Trivalent other) {
        return fromInt(toInt() * other.toInt());
    }

    /**
     * {@code Trivalent} {@code XOR}.
     * @param other the other instance to compare.
     * @return the result of this {@code XOR} the argument.
     */
    public Trivalent xor(Trivalent other) {
        return fromInt(-toInt() * other.toInt());
    }

    /**
     * {@code Trivalent} {@code AND}.
     * @param other the other instance to compare.
     * @return the result of this {@code AND} the argument.
     */
     public Trivalent and(Trivalent other) {
         @SuppressWarnings("UnnecessaryLocalVariable")
         final var comb = switch (toInt() + other.toInt()) {
             case -2, -1 -> FALSE;
             case 2 -> TRUE;
             case 1 -> UNKNOWN;
             default -> toInt() * other.toInt() == 0 ? UNKNOWN : FALSE;
         };
         return comb;
    }

    /**
     * {@code Trivalent} {@code OR}.
     * @param other the other instance to compare.
     * @return the result of this {@code OR} the argument.
     */
    public Trivalent orr(Trivalent other) {
        final int tint = toInt();
        final int oint = other.toInt();
        return tint * oint == 0 ? UNKNOWN
                : tint + oint >= 0 ? TRUE : FALSE;
    }
}
