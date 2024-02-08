/*
 * Copyright © 2024, Lewis S. Bloch. All rights reserved.
 */

package com.lewscanon.etude.game.chess;

public enum Player {
    WHITE(0),
    BLACK(1);

    private final int playOrder;

    Player(int playOrder) {
        this.playOrder = playOrder;
    }
}
