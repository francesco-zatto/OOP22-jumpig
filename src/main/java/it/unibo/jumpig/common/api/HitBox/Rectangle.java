package it.unibo.jumpig.common.api.hitbox;

/**
 * The class to manage the rectangular area of the HitBox.
 */

public class Rectangle implements ShapeHitBox {

    private double x;
    private double y;
    private double width;
    private double height;

    /**
     * Constructor for the Rectangle.
     * 
     * @param x  the abscissa from which the Rectangle begins
     * @param y  the ordinate from which the Rectangle begins
     * @param width  the width of the Rectangle
     * @param height  the height of the Rectangle
     */
    public Rectangle(final double x, final double y, final double width, final double height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * The method to compute the area of the Rectangle which is base multiplied by height.
     * @return the computed area of the Rectangle
     */
    @Override
    public double computeArea() {
        return 0.55; //TODO
    }
}
