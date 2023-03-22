package it.unibo.jumpig.model.api.collision;

import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Interface whose implementations have to handle collisions between
 * player and a gameEntity.
 * @param <E> any kind of gameEntity that the player could collide with.
 */
public interface CollisionHandler<E extends GameEntity> {
    /**
     * Method that handles a collision between player and a gameEntity.
     * @param player player that collided.
     * @param gameEntity gameEntity which the player collided with.
     */
    void handle(Player player, E gameEntity);
}
