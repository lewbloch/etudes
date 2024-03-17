/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.booleanity.trivalence;

/** Trivalent (3-valued) logic.
 * <br>Three-valued logic implementation with integer tricks for the heavy lifting.
 * <br>You'll enjoy the algorithms and the optimizations immutability empowers.
 * <br>
 * Referencing
 * <a href="https://homepage.cs.uiowa.edu/~dwjones/ternary/logic.shtml">Standard Ternary Logic</a>
 * by Douglas W. Jones <br>
 * The University of Iowa Department of Computer Science <br>
 * <br>
 * <h2>Monadic functions, all</h2>
 * <pre>
 * op | 0  1  2  3  4  5  6  7  8  9  A  B  C  D  E  F  G  H  K  M  N  P  R  T  V  X  Z
 * ---+--------------------------------------------------------------------------------
 *  – | –  0  +  –  0  +  –  0  +  –  0  +  –  0  +  –  0  +  –  0  +  –  0  +  –  0  +
 *  0 | –  –  –  0  0  0  +  +  +  –  –  –  0  0  0  +  +  +  –  –  –  0  0  0  +  +  +
 *  + | –  –  –  –  –  –  –  –  –  0  0  0  0  0  0  0  0  0  +  +  +  +  +  +  +  +  +
 * </pre>
 * <h2>Monadic functions, implemented here</h2>
 * <pre>
 *    | FAL NOT INC DEC BNF BNT TRU
 * op |  0   5   7   B   K   V   0
 * ---+-----------------------------
 *  – |  –   +   0   +   –   –   –
 *  0 |  –   0   +   –   –   +   –
 *  + |  –   –   –   0   +   +   –
 *
 * FAL - FALSE
 * NOT - negate
 * INC - increment
 * DEC - decrement
 * BNF - binary FALSE-biased
 * BNT - binary TRUE-biased
 * TRU - TRUE
 * </pre>
 * <h2>Dyadic functions implemented here</h2>
 * <pre>
 * op0 op1 | EQV XOR AND ORR
 * --------+-----------------
 *  –   –  |  +   -   -   -
 *  –   0  |  0   0   -   0
 *  –   +  |  -   +   -   +
 *  0   –  |  0   0   -   0
 *  0   0  |  0   0   0   0
 *  0   +  |  0   0   0   +
 *  +   –  |  –   +   –   +
 *  +   0  |  0   0   0   +
 *  +   +  |  +   –   +   +
 *
 * EQV - equivalent, product
 * XOR - XOR, distinct, negative product
 * AND - AND, both true, minimum
 * ORR - OR, either true, maximum
 * </pre>
 */
public enum Trivalent {
    /** FALSE */    FALSE,
    /** UNKNOWN */  UNKNOWN,
    /** TRUE */     TRUE
    ;

    private static final int NK = values().length;

    /**
     * Obtain the constant from the secret {@code int} value.
     * {@code package-private} access.
     * @param value the {@code int} in the range {@code -1..1}.
     * @return the corresponding constant.
     */
    static Trivalent fromInt(int value) {
        final int ord = (value + 1) % NK;
        return values()[ord];
    }

    /**
     * Safe {@code toInt()}, returns {@code 0} for {@code null}.
     * @param other possibly {@code null} value.
     * @return Either {@code 0} for {@code null} or {@code other.toInt()}.
     */
    static int safeInt(Trivalent other) {
        return other == null ? 0 : other.toInt();
    }

    /**
     * Convert to the secret {@code int}.
     * {@code package-private} access.
     * @return the secret {@code int} in the range {@code -1..1}.
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
        return fromInt(toInt() * safeInt(other));
    }

    /**
     * {@code Trivalent} {@code XOR}.
     * @param other the other instance to compare.
     * @return the result of this {@code XOR} the argument.
     */
    public Trivalent xor(Trivalent other) {
        return fromInt(-toInt() * safeInt(other));
    }

    /**
     * {@code Trivalent} {@code AND}.
     * @param other the other instance to compare.
     * @return the result of this {@code AND} the argument.
     */
     public Trivalent and(Trivalent other) {
         @SuppressWarnings("UnnecessaryLocalVariable")
         final var comb = fromInt(Math.min(toInt(), safeInt(other)));
         return comb;
    }

    /**
     * {@code Trivalent} {@code OR}.
     * @param other the other instance to compare.
     * @return the result of this {@code OR} the argument.
     */
    public Trivalent orr(Trivalent other) {
        @SuppressWarnings("UnnecessaryLocalVariable")
        final var comb = fromInt(Math.max(toInt(), safeInt(other)));
        return comb;
    }
}
