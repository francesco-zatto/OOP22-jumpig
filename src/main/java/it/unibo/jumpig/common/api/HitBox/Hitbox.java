package it.unibo.jumpig.common.api.hitbox;

import it.unibo.jumpig.common.api.Position;

/**
 * The interface to manage the existence area(HitBox) of each game entity.
 */

public interface Hitbox {

    /**
     * The method to get the HitBox in the form of a Rectangle.
     * @return the Rectangle that rapresents the HitBox
     */
    Rectangle getBounds();

    /**
     * The method to update the HitBox position.
     * @param xy  the abscissa and the ordinate from which the Rectangle begins
     * @param width  the width of the Rectangle
     * @param height  the height of the Rectangle
     * @return  the Rectangle created that is the updated HitBox
     */
    Rectangle updateHitBox(final Position xy, final double width, final double height);
}
