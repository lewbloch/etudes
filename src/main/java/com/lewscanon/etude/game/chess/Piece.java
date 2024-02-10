/* Copyright © 2024, Lewis S. Bloch. All rights reserved. */
package com.lewscanon.etude.game.chess;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Chess piece.
 * Constants can have static members. HAH!
 */
@SuppressWarnings("unused")
public enum Piece {
    PAWN {
        @Override
        public Set<Position> moves(Position currentPosition) {
            final Set<Position> legals = new HashSet<>();

            final BoardRank nextRank = currentPosition.getRank().next();
            if (nextRank != null) {
                legals.add(new Position(currentPosition.getFile(), nextRank));
            }

            if (currentPosition.getRank() == BoardRank.R5) {    // en passant
                final BoardFile leftFile = currentPosition.getFile().prev();
                if (leftFile != null) {
                    legals.add(new Position(leftFile, nextRank));
                }
                final BoardFile rightFile = currentPosition.getFile().next();
                if (rightFile != null) {
                    legals.add(new Position(rightFile, nextRank));
                }
            }
            return Collections.unmodifiableSet(legals);
        }
    },

    BISHOP {
        @Override
        public Set<Position> moves(Position currentPosition) {
            final Set<Position> legals = new HashSet<>();

            diagonals(legals, currentPosition);

            return Collections.unmodifiableSet(legals);
        }
    },

    KNIGHT {
        private static final int KLIMIT = 3;
        private static final List<Integer> rankSteps = List.of(-2, -1, 1, 2);

        @Override
        public Set<Position> moves(Position currentPosition) {
            final Set<Position> legals = new HashSet<>();

            for (int rankStep : rankSteps) {
                final BoardRank nextRank = currentPosition.getRank().up(rankStep);

                if (nextRank != null) {
                    final int fileDiff = KLIMIT - Math.abs(rankStep);
                    for (int fileStep : List.of(-fileDiff, fileDiff)) {
                        final BoardFile nextFile = currentPosition.getFile().right(fileStep);
                        if (nextFile != null) {
                            legals.add(new Position(nextFile, nextRank));
                        }
                    }
                }
            }

            return Collections.unmodifiableSet(legals);
        }
    },

    ROOK {
        @Override
        public Set<Position> moves(Position currentPosition) {
            final Set<Position> legals = new HashSet<>();

            return Collections.unmodifiableSet(legals);
        }
    },

    QUEEN {
        @Override
        public Set<Position> moves(Position currentPosition) {
            final Set<Position> legals = new HashSet<>();

            return Collections.unmodifiableSet(legals);
        }
    },

    KING {
        @Override
        public Set<Position> moves(Position currentPosition) {
            final Set<Position> legals = new HashSet<>();

            return Collections.unmodifiableSet(legals);
        }
    }
    ;

    /**
     * Reveal legal positions reachable from a current position.
     * Does not account for game situation, blocking pieces, exposing check, etc.
     * @param currentPosition start point.
     * @return set of legal end points.
     */
    public abstract Set<Position> moves(Position currentPosition);

    static void diagonals(Set<Position> legals, Position currentPosition) {
        BoardRank rank = currentPosition.getRank().prev();
        for (BoardFile file = currentPosition.getFile().prev(); file != null && rank != null;
             file = file.prev(), rank = rank.prev()) {
            legals.add(new Position(file, rank));
        }

        rank = currentPosition.getRank().prev();
        for (BoardFile file = currentPosition.getFile().next(); file != null && rank != null;
             file = file.prev(), rank = rank.next()) {
            legals.add(new Position(file, rank));
        }

        rank = currentPosition.getRank().next();
        for (BoardFile file = currentPosition.getFile().prev(); file != null && rank != null;
             file = file.prev(), rank = rank.next()) {
            legals.add(new Position(file, rank));
        }

        rank = currentPosition.getRank().prev();
        for (BoardFile file = currentPosition.getFile().next(); file != null && rank != null;
             file = file.prev(), rank = rank.next()) {
            legals.add(new Position(file, rank));
        }
    }

    static void diagonalsByFile(Set<Position> legals, BoardFile currentFile, BoardRank rank) {
    }

    static void orthogonals(Set<Position> legals, Position currentPosition) {
        for (BoardRank rank = currentPosition.getRank().prev(); rank != null; rank = rank.prev()) {
            orthogonalsByFile(legals, currentPosition.getFile(), rank);
        }
        for (BoardRank rank = currentPosition.getRank().next();
             rank != null; rank = currentPosition.getRank().next()) {
            orthogonalsByFile(legals, currentPosition.getFile(), rank);
        }
    }

    static void orthogonalsByFile(Set<Position> legals, BoardFile currentFile, BoardRank rank) {
        for (BoardFile file = currentFile.prev(); file != null; file = currentFile.prev()) {
            legals.add(new Position(file, rank));
        }
        for (BoardFile file = currentFile.next(); file != null; file = currentFile.next()) {
            legals.add(new Position(file, rank));
        }
    }
}
