package it.unibo.jumpig.common.api.hitbox;

import it.unibo.jumpig.common.api.Position;

/**
 * The interface to manage the existence area(HitBox) of each game entity.
 */

public interface Hitbox<Shape extends ShapeHitbox> {

    /**
     * The method to get the HitBox in the form of a Rectangle.
     * @return the shape that rapresents the HitBox
     */
    Shape getBounds();

    /**
     * The method to update the HitBox position.
     * @param xy  the abscissa and the ordinate from which the Rectangle begins
     * @param width  the width of the Rectangle
     * @param height  the height of the Rectangle
     * @return the shape created that is the updated HitBox
     */
    Shape updateHitBox(Position xy, double width, double height);
}
