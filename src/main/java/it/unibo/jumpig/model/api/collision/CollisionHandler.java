package it.unibo.jumpig.model.api.collision;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Interface whose implementations have to handle possible collisions between
 * player and a gameEntity. 
 * @param <H> any kind of Hitbox
 * @param <E> any kind of gameEntity that the player could collide with
 */
public interface CollisionHandler<H extends Hitbox, E extends GameEntity<H>> {
    /**
     * Method that handles a possible collision between player and a gameEntity.
     * @param player player that collided.
     * @param gameEntity gameEntity which the player collided with.
     */
    void handle(Player player, E gameEntity);
}
