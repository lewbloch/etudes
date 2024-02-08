/*
 * Copyright © 2024, Lewis S. Bloch. All rights reserved.
 */

package com.lewscanon.etude.game.chess;

import java.util.Map;

/**
 * A rank on the board.
 */
public enum BoardRank {
    R1('1'),
    R2('2'),
    R3('3'),
    R4('4'),
    R5('5'),
    R6('6'),
    R7('7'),
    R8('8'),
    ;

    private static final Map<Character, BoardRank> FROM_CHARS = Map.of(
            R1.toChar(), R1,
            R2.toChar(), R2,
            R3.toChar(), R3,
            R4.toChar(), R4,
            R5.toChar(), R5,
            R6.toChar(), R6,
            R7.toChar(), R7,
            R8.toChar(), R8
    );

    /**
     * Find the rank from its label.
     *
     * @param label the label to find.
     * @return the corresponding {@link BoardRank} or {@code null} if not found.
     */
    public static BoardRank fromChar(char label) {
        return FROM_CHARS.getOrDefault(label, null);
    }

    /**
     * Find the rank from its label.
     *
     * @param label the label to find.
     * @return the corresponding {@link BoardRank} or {@code null} if not found.
     */
    public static BoardRank fromInt(int label) {
        return label > Character.MAX_VALUE ? null : fromChar((char) label);
    }

    private final char rankch;
    private final String represent;

    BoardRank(char rankch) {
        this.rankch = rankch;
        this.represent = String.valueOf(this.rankch);
    }

    @Override
    public String toString() {
        return represent;
    }

    /**
     * Get the board rank label.
     * @return the board rank label.
     */
    public char toChar() {
        return rankch;
    }

    /**
     * The next rank.
     * @return The next rank or {@code null} if past the last one.
     */
    public final BoardRank next() {
        return up(1);
    }

    /**
     * The previous rank.
     * @return the previous rank or {@code null} if past the first one.
     */
    public final BoardRank prev() {
        return down(1);
    }

    /**
     * Return the rank up {@code squares} from this rank.
     * @param squares how many squares up.
     * @return the {@link BoardRank} that many {@code squares} up or {@code null} if too far.
     */
    public final BoardRank up(int squares) {
        final int next = ordinal() + squares;
        return next < 0 || next >= values().length ? null : values()[next];
    }

    /**
     * Return the rank down {@code squares} from this rank.
     * @param squares how many squares down.
     * @return the {@link BoardRank} that many {@code squares} down or {@code null} if too far.
     */
    public final BoardRank down(int squares) {
        return up(-squares);
    }
}
