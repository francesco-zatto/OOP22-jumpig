package it.unibo.jumpig.common.api.hitbox;

import it.unibo.jumpig.common.api.Position;

/**
 * The interface to manage the existence area(Hitbox) of each game entity.
 * @param <S> is the type of ShapeHitbox.
 */

public interface Hitbox {

    /**
     * The method to get the center of the shape.
     * @return the center of the shape.
     */
    Position getCenter();

    /**
     * The method to get the Hitbox in its form.
     * @return the shape that rapresents the Hitbox
     */
    S getBounds();

    /**
     * The method to update the HitBox position.
     * @param center  the abscissa and the ordinate of the center of the Hitbox.
     * @return the shape created that is the updated Hitbox
     */
     S updateHitBox(Position center);
}
