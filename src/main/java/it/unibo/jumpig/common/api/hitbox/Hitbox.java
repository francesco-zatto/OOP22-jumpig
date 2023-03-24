package it.unibo.jumpig.common.api.hitbox;

import it.unibo.jumpig.common.api.Position;

/**
 * The interface to manage the existence area(Hitbox) of each game entity.
 */

public interface Hitbox<Shape extends ShapeHitbox> {

    /**
     * The method to get the Hitbox in its form.
     * @return the shape that rapresents the Hitbox
     */
    Shape getBounds();

    /**
     * The method to update the HitBox position.
     * @param position  the abscissa and the ordinate from which the Hitbox begins
     * @param width  the width of the Hitbox
     * @param height  the height of the Hitbox
     * @return the shape created that is the updated Hitbox
     */
    Shape updateHitBox(Position position, double width, double height);
}
