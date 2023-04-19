package it.unibo.jumpig.model.api.collision;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Abstract class of CollisionChecker that implements the way every collision is checked.
 * @param <H> any kind of hitbox
 * @param <E> any kind of game entity
 */
public abstract class AbstractCollisionChecker<H extends Hitbox, E extends GameEntity<H>> implements CollisionChecker<H, E> {

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean check(final Player player, final E gameEntity) {
        return this.canPlayerCollide(player)
            && this.canEntityCollide(gameEntity)
            && this.areBoundsColliding(player, gameEntity);
    }

    /**
     * Method that checks if the hitboxes are colliding.
     * @param player player to check if it is colliding.
     * @param gameEntity gameEntity to check if it is coliding.
     * @return true if player's and gameEntity's bounds are colliding.
     */
    protected abstract boolean areBoundsColliding(Player player, E gameEntity);

    /**
     * Method that checks if the player in this moment can collide or not.
     * @param player player to check.
     * @return true if the player can collide.
     */
    protected abstract boolean canPlayerCollide(Player player);

    /**
     * Method that checks if the player in this moment can collide or not.
     * @param gameEntity gameEntity to check.
     * @return true if the gameEntity can collide.
     */
    protected abstract boolean canEntityCollide(E gameEntity);

    /**
     * Utility method used to create the collisionChecker that checks if a number is in a range (min, max).
     * @param num number to check if is in range.
     * @param min the minimum of the range.
     * @param max the maximum of the range.
     * @return whether or not num is in range(min, max).
     */
    protected static final boolean isBetween(final double num, final double min, final double max) {
        return min < num && num < max;
    }
}
