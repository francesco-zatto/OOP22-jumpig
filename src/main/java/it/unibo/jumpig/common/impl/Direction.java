package it.unibo.jumpig.common.impl;

/**
 * Enum for the horizontal direction.
 */
public enum Direction {
    /**
     * The left direction.
     */
    HORIZONTAL_SX(-1),
    /**
     * The right direction.
     */
    HORIZONTAL_DX(1),
    /**
     * The direction zero, there's no horizontal movement.
     */
    HORIZONTAL_ZERO(0);

    private final int actualDirection;

    /**
     * Constructor that associates the enum with a number.
     * @param actualDirection the value of the direction
     */
    Direction(final int actualDirection) {
        this.actualDirection = actualDirection;
    }

    /**
     * Getter for the direction.
     * @return the direction
     */
    public int getDirection() {
        return this.actualDirection;
    }
}
