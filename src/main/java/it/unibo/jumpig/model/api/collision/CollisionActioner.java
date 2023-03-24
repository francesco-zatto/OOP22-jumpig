package it.unibo.jumpig.model.api.collision;

import it.unibo.jumpig.model.api.gameentity.GameEntity;

/**
 * Functional interface that acts in a specific way when a player and
 * a gameEntity collide.
 * @param <E> any kind of gameEntity that the player collided with
 */
public interface CollisionActioner<E extends GameEntity> {
}
