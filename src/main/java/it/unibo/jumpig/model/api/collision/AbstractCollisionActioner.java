package it.unibo.jumpig.model.api.collision;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Abstract class that implements the way the player and the gameEntity behave after a collision.
 * @param <H> any kind of Hitbox
 * @param <E> any kind of gameEntity that the player could collide with
 */
public abstract class AbstractCollisionActioner<H extends Hitbox, E extends GameEntity<H>> implements CollisionActioner<H, E> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void act(final Player player, final E gameEntity) {
        this.actOnPlayer(player);
        this.actOnEntity(gameEntity);
    }

    /**
     * Method that changes the behaviour of player.
     * @param player player that collided.
     */
    protected abstract void actOnPlayer(Player player);

    /**
     * Method that changes the behaviour of gameEntity.
     * @param gameEntity gameEntity that collided.
     */
    protected abstract void actOnEntity(E gameEntity);
}
