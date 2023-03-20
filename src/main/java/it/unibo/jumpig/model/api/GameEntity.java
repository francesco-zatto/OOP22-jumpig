package it.unibo.jumpig.model.api;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * Interface that represent an entity in the game.
 */
public interface GameEntity {
    /**
     * Getter for the position of the gameEntity.
     * @return the gameEntity's position.
     */
    Position getPosition();

    /**
     * Getter for the hitbox of the gameEntity.
     * @return the gameEntity's hitbox.
     */
    Hitbox getHitbox();

}
