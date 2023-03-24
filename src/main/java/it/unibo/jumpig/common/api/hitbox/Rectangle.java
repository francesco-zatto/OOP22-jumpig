package it.unibo.jumpig.common.api.hitbox;

import it.unibo.jumpig.common.api.Position;

/**
 * The class to manage the rectangular area of the HitBox.
 */

public class Rectangle implements ShapeHitbox {

    private final Position position;
    private final double width;
    private final double height;

    /**
     * Constructor for the Rectangle.
     * 
     * @param position  the abscissa and the ordinate of the center of the Rectangle.
     * @param width  the width of the Rectangle
     * @param height  the height of the Rectangle
     */
    public Rectangle(final Position position, final double width, final double height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }
  
    /**
     * The method to get the abscissa of the center of the Rectangle.
     * @return x
     */
    public double getX() {
        return this.position.getX();
    }

    /**
     * The method to get the ordinate of the center of the Rectangle.
     * @return y
     */
    public double getY() {
        return this.position.getY();
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
    
    @Override
    public Position getCenter() {
        return this.position;
    }
}
