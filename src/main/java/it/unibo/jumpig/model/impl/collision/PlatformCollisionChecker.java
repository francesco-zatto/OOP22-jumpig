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
        return isPlayerAligned(playerHitbox, platformHitbox) && isPlayerAbove(playerHitbox, platformHitbox);
    }

    private boolean isPlayerAligned(final RectangleHitbox playerHitbox, final RectangleHitbox platformHitbox) {
        final double playerLeftX = playerHitbox.getLeftX();
        final double playerRightX = playerHitbox.getRightX();
        final double platformLeftX = platformHitbox.getLeftX();
        final double platformRightX = platformHitbox.getRightX();
        return super.isBetween(playerLeftX, platformLeftX, platformRightX) 
            || super.isBetween(playerRightX, platformLeftX, platformRightX);
    }

    private boolean isPlayerAbove(final RectangleHitbox playerHitbox, final RectangleHitbox platformHitbox) {
        return isBetween(
            playerHitbox.getLowerY(), 
            platformHitbox.getCenter().getY(), 
            platformHitbox.getUpperY()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean canPlayerCollide(final Player player) {
        return player.getVelocity().getYComponent() < 0;
    }
}
