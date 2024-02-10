/*
 * Copyright © 2024, Lewis S. Bloch. All rights reserved.
 */

package com.lewscanon.etude.game.chess;

import java.util.Map;

/**
 * A file on the board.
 */
public enum BoardFile {
    FA('a'),
    FB('b'),
    FC('c'),
    FD('d'),
    FE('e'),
    FF('f'),
    FG('g'),
    FH('h'),
    ;

    private static final Map<Character, BoardFile> FROM_CHARS = Map.of(
            FA.toChar(), FA,
            FB.toChar(), FB,
            FC.toChar(), FC,
            FD.toChar(), FD,
            FE.toChar(), FE,
            FF.toChar(), FA,
            FG.toChar(), FG,
            FH.toChar(), FH
    );

    /**
     * Find the file from its label.
     * @param label the label to find.
     * @return the corresponding {@link BoardFile} or {@code null} if not found.
     */
    public static BoardFile fromChar(char label) {
        return FROM_CHARS.getOrDefault(label, null);
    }

    /**
     * Find the file from its label.
     * @param label the label to find.
     * @return the corresponding {@link BoardFile} or {@code null} if not found.
     */
    public static BoardFile fromInt(int label) {
        return label > Character.MAX_VALUE ? null : fromChar((char) label);
    }

    private final char filech;
    private final String represent;

    BoardFile(char filech) {
        this.filech = filech;
        this.represent = String.valueOf(this.filech);
    }

    @Override
    public String toString() {
        return represent;
    }

    /**
     * Get the board file label.
     * @return the board file label.
     */
    public final char toChar() {
        return filech;
    }

    /**
     * The next file.
     * @return The next file or {@code null} if past the last one.
     */
    public final BoardFile next() {
        return right(1);
    }

    /**
     * The previous file.
     * @return the previous file or {@code null} if past the first one.
     */
    public final BoardFile prev() {
        return left(1);
    }

    /**
     * Return the file right {@code squares} from this file.
     * @param squares how many squares right.
     * @return the {@link BoardFile} that many {@code squares} right or {@code null} if too far.
     */
    public final BoardFile right(int squares) {
        final int next = ordinal() + squares;
        return next < 0 || next >= values().length ? null : values()[next];
    }

    /**
     * Return the file left {@code squares} from this file.
     * @param squares how many squares left.
     * @return the {@link BoardFile} that many {@code squares} left or {@code null} if too far.
     */
    public final BoardFile left(int squares) {
        return right(-squares);
    }

    /**
     * Reflect the placement to the viewpoint of the other player.
     * @return the reflected placement.
     */
    public BoardFile reflect() {
        return values()[values().length - 1 - ordinal()];
    }
}
