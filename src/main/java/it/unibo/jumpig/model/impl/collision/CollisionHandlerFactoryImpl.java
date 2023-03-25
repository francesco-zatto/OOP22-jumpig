package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.impl.hitbox.Rectangle;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.CollisionHandler;
import it.unibo.jumpig.model.api.collision.CollisionHandlerFactory;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that produces any kind of collisionHandler for any kind of
 * collision that can happen in the game.
 */
public class CollisionHandlerFactoryImpl implements CollisionHandlerFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionHandler<Rectangle, RectangleHitbox, Platform> createPlatformCollisionHandler() {
        return new CollisionHandlerImpl<>(this::isPlayerJumpingOnPlatform, this::playerJumps);
    }

    private boolean isPlayerJumpingOnPlatform(final Player player, final Platform platform) {
        final Rectangle playerShape = player.getHitbox().getBounds();
        final Rectangle platformShape = platform.getHitbox().getBounds();
        final double playerLeftX = this.getRectangleX(playerShape, true);
        final double playerRightX = this.getRectangleX(playerShape, false);
        final double platformLeftX = this.getRectangleX(platformShape, true);
        final double platformRightX = this.getRectangleX(platformShape, false);
        //final double playerLowerY = this.getRectangleY(playerShape, true);
        //final double platformUpperY = this.getRectangleY(platformShape, false);
        return playerLeftX < platformLeftX && playerRightX > platformLeftX
                || playerLeftX < platformRightX && playerRightX > platformRightX;
        //return isPlayerAligned; 
    }

    private double getRectangleX(final Rectangle rectangle, final boolean left) {
        return rectangle.getCenter().getX() + (left ? -1 : +1) * (rectangle.getWidth() / 2);
    }

    /*private double getRectangleY(final Rectangle rectangle, final boolean lower) {
        return rectangle.getCenter().getY() + (lower ? -1 : +1) * (rectangle.getHeight() / 2);
    }*/

    private void playerJumps(final Player player, final Platform platform) {
        player.setVelocityFromJump(platform.getJumpVelocity());
    }
}
