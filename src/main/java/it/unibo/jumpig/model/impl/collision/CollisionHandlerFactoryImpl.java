package it.unibo.jumpig.model.impl.collision;

import java.util.function.Function;

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
        final boolean isPlayerAligned = this.getRectangleLeftX(playerShape) < this.getRectangleLeftX(platformShape)
                && this.getRectangleRightX(playerShape) > this.getRectangleLeftX(platformShape)
                || this.getRectangleLeftX(playerShape) < this.getRectangleRightX(platformShape) 
                && this.getRectangleRightX(playerShape) > this.getRectangleRightX(platformShape);
        final boolean isPlayerAbove = this.getRectangleLowerY(playerShape) < this.getRectangleUpperY(platformShape)
                && this.getRectangleLowerY(playerShape) > platformShape.getY();
        return isPlayerAligned && isPlayerAbove;
    }

    private double getRectangleLeftX(final Rectangle rectangle) {
        return this.getRectangleCoordinate(rectangle, true, Rectangle::getX, Rectangle::getWidth);
    }

    private double getRectangleRightX(final Rectangle rectangle) {
        return this.getRectangleCoordinate(rectangle, false, Rectangle::getX, Rectangle::getWidth);
    }

    private double getRectangleLowerY(final Rectangle rectangle) {
        return this.getRectangleCoordinate(rectangle, true, Rectangle::getY, Rectangle::getHeight);
    }

    private double getRectangleUpperY(final Rectangle rectangle) {
        return this.getRectangleCoordinate(rectangle, false, Rectangle::getX, Rectangle::getWidth);
    }

    /*The boolean flag is true for lowerY and leftX, because this method to get those coordinates has to
     * subtract the dimension from the center coordinate. Instead, flag is false for upperY and rightY.
    */
    private double getRectangleCoordinate(final Rectangle rectangle, final boolean flag, 
            final Function<Rectangle, Double> getCenterCoordinate, final Function<Rectangle, Double> getDimension) {
        return getCenterCoordinate.apply(rectangle) + (flag ? -1 : +1) * (getDimension.apply(rectangle) / 2);
    }

    private void playerJumps(final Player player, final Platform platform) {
        player.setVelocityFromJump(platform.getJumpVelocity());
    }
}
