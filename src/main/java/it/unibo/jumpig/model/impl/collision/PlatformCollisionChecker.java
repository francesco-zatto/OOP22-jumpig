package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.AbstractCollisionChecker;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that checks if a player is jumping on a platform.
 * @param <P> any kind of platform.
 */
public abstract class PlatformCollisionChecker<P extends Platform> extends AbstractCollisionChecker<RectangleHitbox, P> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean areBoundsColliding(final Player player, final P gameEntity) {
        final RectangleHitbox playerHitbox = player.getHitbox();
        final RectangleHitbox platformHitbox = gameEntity.getHitbox();
        final double playerLeftX = playerHitbox.getRectangleLeftX();
        final double playerRightX = playerHitbox.getRectangleRightX();
        final double platformLeftX = platformHitbox.getRectangleLeftX();
        final double platformRightX = platformHitbox.getRectangleRightX();
        final boolean isPlayerAligned = super.isBetween(playerLeftX, platformLeftX, platformRightX) 
                || super.isBetween(playerRightX, platformLeftX, platformRightX);
        final boolean isPlayerAbove = isBetween(playerHitbox.getRectangleLowerY(), platformHitbox.getCenter().getY(),
                platformHitbox.getRectangleUpperY());
        return isPlayerAligned && isPlayerAbove;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean canPlayerCollide(final Player player) {
        return player.getVelocity().getYComponent() < 0;
    }
}
