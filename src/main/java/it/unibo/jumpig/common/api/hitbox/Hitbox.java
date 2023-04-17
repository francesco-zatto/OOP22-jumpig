package it.unibo.jumpig.common.api.hitbox;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.model.api.gameentity.Renderable;

/**
 * The interface to manage the existence area(Hitbox) of each game entity.
 */
public interface Hitbox extends Renderable {

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
