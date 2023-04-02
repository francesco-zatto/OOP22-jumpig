package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.AbstractCollisionHandler;
import it.unibo.jumpig.model.api.collision.CollisionActioner;
import it.unibo.jumpig.model.api.collision.CollisionChecker;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that handles collisions between a player and a platform, checking if the player is jumping on 
 * the platform and causing his jump.
 */
public class BasicPlatformCollisionHandler extends AbstractCollisionHandler<RectangleHitbox, Platform> {
    
    @Override
    protected CollisionChecker<RectangleHitbox, Platform> getCollisionChecker() {
        return this::isPlayerJumpingOnPlatform;
    }

    private boolean isPlayerJumpingOnPlatform(final Player player, final Platform platform) {
        if (player.getVelocity().getYComponent() >= 0) {
            return false;
        }
        final RectangleHitbox playerHitbox = player.getHitbox();
        final RectangleHitbox platformHitbox = platform.getHitbox();
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

    @Override
    protected CollisionActioner<RectangleHitbox, Platform> getCollisionActioner() {
        return this::playerJumps;
    }

    private void playerJumps(final Player player, final Platform platform) {
        player.setVelocityFromJump(platform.getJumpVelocity());
    }    
}
