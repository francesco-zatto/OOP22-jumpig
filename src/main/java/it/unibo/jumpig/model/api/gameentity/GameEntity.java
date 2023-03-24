package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.common.api.hitbox.ShapeHitbox;

/**
 * Interface that represent an entity in the game.
 * @param <S> any kind of ShapeHitbox
 * @param <H> any kind of Hitbox
 */
public interface GameEntity<S extends ShapeHitbox, H extends Hitbox<S>> {
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
