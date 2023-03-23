package it.unibo.jumpig.model.api.collision;

import it.unibo.jumpig.model.api.gameentity.GameEntity;

/**
 * Functional interface that checks a possible collision of a player
 * with a gameEntity.
 * @param <E> any kind of gameEntity that the player could collide with.
 */
public interface CollisionChecker<E extends GameEntity> {
    
}
