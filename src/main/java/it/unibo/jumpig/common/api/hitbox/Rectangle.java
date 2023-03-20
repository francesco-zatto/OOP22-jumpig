package it.unibo.jumpig.common.api.hitbox;

import it.unibo.jumpig.common.api.Position;

/**
 * The class to manage the rectangular area of the HitBox.
 */

public class Rectangle implements ShapeHitBox {

    private final Position xy;
    private final double width;
    private final double height;

    /**
     * Constructor for the Rectangle.
     * 
     * @param xy  the abscissa and the ordinate from which the Rectangle begins
     * @param width  the width of the Rectangle
     * @param height  the height of the Rectangle
     */
    public Rectangle(final Position xy, final double width, final double height) {
        this.xy = xy;
        this.width = width;
        this.height = height;
    }

    /**
     * The method to compute the area of the Rectangle which is base multiplied by height.
     * @return the computed area of the Rectangle
     */
    @Override
    public double computeArea() {
        return this.getWidth() * this.getHeight();
    }

    /**
     * The method to get the abscissa from which the Rectangle begins.
     * @return x
     */
    public double getX() {
        return this.xy.getX();
    }

    /**
     * The method to get the ordinate from which the Rectangle begins.
     * @return y
     */
    public double getY() {
        return this.xy.getY();
    }

    /**
     * The method to get width.
     * @return width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * The method to get height.
     * @return height
     */
    public double getHeight() {
        return this.height;
    }
}
