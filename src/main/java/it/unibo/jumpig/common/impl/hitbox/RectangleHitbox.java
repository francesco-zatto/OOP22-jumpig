package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * The class to manage a rectangular Hitbox.
 */

public class RectangleHitbox implements Hitbox {

    private Position center;
    private final double width;
    private final double height;

    /**
     * The constructor to create a new rectangular Hitbox.
     * @param center  the abscissa and the ordinate of the center of the rectangular Hitbox.
     * @param width  the width of the rectangular Hitbox.
     * @param height  the height of the rectangular Hitbox.
     */

    public RectangleHitbox(final Position center, final double width, final double height) {
        this.center = center;
        this.width = width;
        this.height = height;
    }

    /**
     * The method to get the height of the rectangular Hitbox.
     * @return height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * The method to get the width of the rectangular Hitbox.
     * @return width
     */
    public double getWidth() {
        return this.width;
    }

    public double getRectangleLeftX() {
        return getRectangleCoordinate(this.center.getX(), this.getWidth(), true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getCenter() {
        return this.center;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHitBox(final Position center) {
        this.center = center;
    }

    /*The boolean isSignNegative is true for lowerY and leftX, because methods to get those coordinates has to
     * subtract the half of dimension from the center coordinate. Instead, isSignNegative is false for upperY and rightY, 
     * because methods to get those coordinates has to add the half of dimension from the center coordinate.
    */
    private double getRectangleCoordinate(final double coordinate, final double dimension, final boolean isSignNegative) {
        return coordinate + (isSignNegative ? -1 : +1) * (dimension / 2);
    }

}
