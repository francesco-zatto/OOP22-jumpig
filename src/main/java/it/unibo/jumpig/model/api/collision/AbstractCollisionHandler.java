package it.unibo.jumpig.model.api.collision;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Abstract class that handles possible collision between a player and a gameEntity.
 * @param <H> any kind of Hitbox
 * @param <E> any kind of gameEntity that the player could collide with
 */
public abstract class AbstractCollisionHandler<H extends Hitbox, E extends GameEntity<H>> implements CollisionHandler<H, E> {
    /**
     * Method that, given a collisionChecker and a collisionActioner, checks a possible 
     * collision and then it acts on the player and the other gameEntity that have collided.
    */
    @Override
    public final void handle(final Player player, final E gameEntity) {
        if (this.getCollisionChecker().check(player, gameEntity)) {
            this.getCollisionActioner().act(player, gameEntity);
        }
    }

    protected abstract CollisionChecker<H, E> getCollisionChecker();

    protected abstract CollisionActioner<H, E> getCollisionActioner();

    protected final boolean isBetween(final double num, final double min, final double max) {
        return min < num && num < max;
    }
}
