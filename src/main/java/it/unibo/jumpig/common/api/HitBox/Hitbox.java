package it.unibo.jumpig.common.api.hitbox;

/**
 * The interface to manage the existence area(HitBox) of each game entity.
 */

public interface Hitbox {

    /**
     * The method to get the HitBox in the form of a Rectangle.
     * @return the Rectangle that rapresents the HitBox
     */
    Rectangle getBounds();
}
