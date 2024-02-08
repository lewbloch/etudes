/*
 * Copyright © 2024, Lewis S. Bloch. All rights reserved.
 */

package com.lewscanon.etude.game.chess;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A chess piece.
 * Constants have their own internal static variables. HAH!
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


            return Collections.unmodifiableSet(legals);
        }
    },

    KNIGHT {
        private static final int KLIMIT = 3;
        private static final int[] rankSteps = {-2, -1, 1, 2};

        @Override
        public Set<Position> moves(Position currentPosition) {
            final Set<Position> legals = new HashSet<>();

            for (int rankStep : rankSteps) {
                final BoardRank nextRank = currentPosition.getRank().up(rankStep);

                if (nextRank != null) {
                    final int fileDiff = KLIMIT - Math.abs(rankStep);
                    for (int fileStep : new int[] {-fileDiff, fileDiff}) {
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
}
