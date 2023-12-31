package com.lewscanon.etude.booleanity;

/**
 * A {@code boolean} triplet, useful for seeing what happens in boolean operations.
 */
public class Trebool implements Comparable<Trebool> {
    static final String LMRF = "{left: %b, middle: %b, right: %b}";
    static final String STATEF = "%03o%n";

    // properties
    boolean left, middle, right;

    private int state = 0;
    private int state() {
        return (left ? 4 : 0) + (middle ? 2 : 0) + (right ? 1 : 0);
    }

    /**
     * Daisy-chain property {@code left}.
     * @param value new value.
     * @return property after being set.
     */
    public boolean left(boolean value) {
        this.left = value;
        this.state = state();
        System.out.printf("L " + STATEF, state);
        return this.left;
    }

    /**
     * Daisy-chain property {@code middle}.
     * @param value new value.
     * @return property after being set.
     */
    public boolean middle(boolean value) {
        this.middle = value;
        this.state = state();
        System.out.printf("M " + STATEF, state);
        return this.middle;
    }

    /**
     * Daisy-chain property {@code right}.
     * @param value new value.
     * @return property after being set.
     */
    public boolean right(boolean value) {
        this.right = value;
        this.state = state();
        System.out.printf("R " + STATEF, state);
        return this.right;
    }

    // fields

    /**
     * Left field.
     * @return field value.
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * Middle field.
     * @return field value.
     */
    public boolean isMiddle() {
        return middle;
    }

    /**
     * Right field.
     * @return field value.
     */
    public boolean isRight() {
        return right;
    }

    // operations

    /**
     * Logical {@code and} mapping {@code (left && middle) -> right}.
     * @return {@code this}.
     */
    public final Trebool and() {
        right(isLeft() && isMiddle());
        return this;
    }

    /**
     * Logical {@code or} mapping {@code (left || middle) -> right}.
     * @return {@code this}.
     */
    public final Trebool or() {
        right(isLeft() || isMiddle());
        return this;
    }

    /**
     * Logical {@code not} mapping {@code (right) -> !right}.
     * @return {@code this}.
     */
    public final Trebool not() {
        right(! isRight());
        return this;
    }

    /**
     * Logical {@code xor} mapping {@code (left ^ middle) -> right}.
     * @return {@code this}.
     */
    public final Trebool xor() {
        right(isLeft() ^ isMiddle());
        return this;
    }

    // value aspect

    @Override
    public int hashCode() {                     // hash is unique to state
        return state;
    }

    @Override
    public boolean equals(Object other) {       // hash is unique to state
        return other instanceof Trebool oboole && hashCode() == oboole.hashCode();
    }

    @Override
    public int compareTo(Trebool oboole) {
        return hashCode() - oboole.hashCode();  // hash is unique to state
    }

    @Override
    public String toString() {
        return String.format(LMRF, left, middle, right);
    }

    /**
     * Exercise the boolean precedence rules.
     * @param args program arguments.
     */
    public static void main(String... args) {
        final Trebool trebool = new Trebool();
        if ( trebool.left(true) || trebool.middle(true) && trebool.right(false) ) {
            System.out.println("expression true");
        }
        else {
            System.out.println("expression false");
        }

        if ( trebool.left(true) | trebool.middle(true) && trebool.right(false) ) {
            System.out.println("expression true");
        }
        else {
            System.out.println("expression false");
        }

        if ( trebool.left(true) | trebool.middle(true) & trebool.right(false) ) {
            System.out.println("expression true");
        }
        else {
            System.out.println("expression false");
        }

        boolean ttf = trebool.left(true) && trebool.middle(true) || trebool.right(false);
        System.out.println("T && T || F " + trebool + " -> " + ttf);
    }
}
