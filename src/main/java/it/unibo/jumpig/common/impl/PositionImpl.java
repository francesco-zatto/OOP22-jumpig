package it.unibo.jumpig.common.impl;

import it.unibo.jumpig.common.api.Position;

/**
 * Class PositionImpl that implemets Position in a Cartesian plane.
 */
public class PositionImpl implements Position {

    private final double x;
    private final double y;

    /**
     * Constructor for the creation of a position.
     * @param x the abscissa
     * @param y the ordinate
     */
    public PositionImpl(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return this.x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return this.y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "PositionImpl [x=" + x + ", y=" + y + "]";
    }
}
