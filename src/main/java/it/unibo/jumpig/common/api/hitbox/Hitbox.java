package it.unibo.jumpig.common.api.hitbox;

import it.unibo.jumpig.common.api.Position;

/**
 * The interface to manage the existence area(Hitbox) of each game entity.
 */
public interface Hitbox {

    /**
     * The method to get the center of the shape.
     * @return the center of the shape.
     */
    Position getCenter();

    /**
     * The method to update the HitBox position.
     * @param center  the abscissa and the ordinate of the center of the Hitbox.
     */
     void updateHitBox(Position center);
}
