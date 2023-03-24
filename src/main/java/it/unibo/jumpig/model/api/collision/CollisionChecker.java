package it.unibo.jumpig.model.api.collision;

import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Functional interface that checks a possible collision of a player
 * with a gameEntity.
 * @param <E> any kind of gameEntity that the player could collide with.
 */
@FunctionalInterface
public interface CollisionChecker<E extends GameEntity> {
    /**
     * Method that checks if the given player and gameEntity are colliding.
     * @param player player which could be in collision.
     * @param gameEntity gameEntity which the player could have collided with.
     * @return true if there is a collision, otherwise false.
     */
    boolean check(Player player, E gameEntity);
}
