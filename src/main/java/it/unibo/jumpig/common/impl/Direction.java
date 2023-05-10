package it.unibo.jumpig.common.impl;

public enum Direction {
    HORIZONTAL_SX(-1),
    HORIZONTAL_DX(1),
    HORIZONTAL_NULL(0);

    private final int actualDirection;

    Direction(final int actualDirection) {
        this.actualDirection = actualDirection;
    }

    public int getDirection() {
        return this.actualDirection;
    }
}
