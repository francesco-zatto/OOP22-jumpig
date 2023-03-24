package it.unibo.jumpig.model.api.collision;

import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Functional interface that acts in a specific way when a player and
 * a gameEntity collide.
 * @param <E> any kind of gameEntity that the player collided with
 */
@FunctionalInterface
public interface CollisionActioner<E extends GameEntity> {
    /**
     * Method that acts on the player and the gameEntity collided.
     * @param player player which collided.
     * @param gameEntity gameEntity which the player collided with.
     */
    void act(Player player, E gameEntity);
}
