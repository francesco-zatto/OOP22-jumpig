package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * Interface that represent an entity in the game.
 * @param <H> any kind of Hitbox
 */
public interface GameEntity<H extends Hitbox> extends Renderable {
    /**
     * Getter for the position of the gameEntity.
     * @return the gameEntity's position.
     */
    Position getPosition();

    /**
     * Getter for the hitbox of the gameEntity.
     * @return the gameEntity's hitbox.
     */
    H getHitbox();

}
