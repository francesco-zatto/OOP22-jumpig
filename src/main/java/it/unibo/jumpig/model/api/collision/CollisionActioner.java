package it.unibo.jumpig.model.api.collision;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.common.api.hitbox.ShapeHitbox;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Functional interface that acts in a specific way when a player and
 * a gameEntity collide.
 * @param <S> any kind of ShapeHitbox
 * @param <H> any kind of Hitbox
 * @param <E> any kind of gameEntity that the player could collide with
 */
@FunctionalInterface
public interface CollisionActioner<S extends ShapeHitbox, H extends Hitbox<S>, E extends GameEntity<S, H>> {
    /**
     * Method that acts on the player and the gameEntity collided.
     * @param player player which collided.
     * @param gameEntity gameEntity which the player collided with.
     */
    void act(Player player, E gameEntity);
}
