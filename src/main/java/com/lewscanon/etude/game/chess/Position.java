/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.game.chess;

/**
 * Position on the chess board.
 * Each {@link Piece} holds its position, updated as it moves.
 * The instances are immutable, so a change in position necessitates a new {@link Position}.
 */
public class Position {
    /** The board file (column). */
    private final BoardFile bfile;

    /* The board rank (row). */
     private final BoardRank brank;

    /** Hash code. */
    private final int hash;
    private int hash() {
        final int SCRAMBLE = 31;
        return SCRAMBLE * bfile.hashCode() + brank.hashCode();
    }

    /** {@link String} representation. */
    private final String position;
    private String represent() {
        return new String(new char[]{this.bfile.toChar(), this.brank.toChar()}).intern();
    }

    /**
     * Constructor, setting the file and rank separately.
     * @param file the file by letter or normalized value.
     * @param rank the rank by letter or normalized value.
     */
    public Position(BoardFile file, BoardRank rank) {
        if (file == null || rank == null) {
            throw new NullPointerException("null argument: {"
                    + (file == null ? " file" : "") + (rank == null ? " rank" : "") + " }");
        }
        this.bfile = file;
        this.brank = rank;

        this.hash = hash();
        this.position = represent();
    }

    /**
     * The board file (column).
     * @return the board file.
     */
    public final BoardFile getFile() {
        return bfile;
    }

    /**
     * The board rank (row).
     * @return the board rank.
     */
    public final BoardRank getRank() {
        return brank;
    }

    @Override
    public String toString() {
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Position opos
                && this.bfile == opos.bfile
                && this.brank == opos.brank;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    public Position otherPlayer() {
        return new Position(bfile, brank.reflect());
    }
}
