package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * Interface that represent an entity in the game.
 * @param <H> any kind of Hitbox
 */
public interface GameEntity<H extends Hitbox> {
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

    /**
     * The method to create a new scaled hitbox which will stay between 0 and the height of the world.
     * @param position the position of the hitbox in thw world.
     * @return the scaled hitbox
     */
    H createScaledHitbox(Position position);

}
