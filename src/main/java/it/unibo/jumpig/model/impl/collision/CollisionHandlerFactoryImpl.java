package it.unibo.jumpig.model.impl.collision;

import java.util.function.Supplier;

import it.unibo.jumpig.common.impl.hitbox.Circle;
import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.common.impl.hitbox.Rectangle;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.CollisionHandler;
import it.unibo.jumpig.model.api.collision.CollisionHandlerFactory;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Enemy;
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

    /*The variable isPlayerAligned indicates if the player has the right abscissas to jump on the platform,
     * i.e. if a part of the player has the same x of the platform. The variable isPlayerAbove indicates if the player is above 
     * the platform and it's touching the superior part of the platform.
     * Also the vertical velocity of the player has to be checked, if it is going down or up.
     */
    private boolean isPlayerJumpingOnPlatform(final Player player, final Platform platform) {
        if (player.getVelocity().getYComponent() >= 0) {
            return false;
        }
        final Rectangle playerShape = player.getHitbox().getBounds();
        final Rectangle platformShape = platform.getHitbox().getBounds();
        final double playerLeftX = getRectangleLeftX(playerShape);
        final double playerRightX = getRectangleRightX(playerShape);
        final double platformLeftX = getRectangleLeftX(platformShape);
        final double platformRightX = getRectangleRightX(platformShape);
        final boolean isPlayerAligned = isBetween(playerLeftX, platformLeftX, platformRightX) 
                || isBetween(playerRightX, platformLeftX, platformRightX);
        final boolean isPlayerAbove = isBetween(getRectangleLowerY(playerShape), platformShape.getY(),
                getRectangleUpperY(platformShape));
        return isPlayerAligned && isPlayerAbove;
    }

    private double getRectangleLeftX(final Rectangle rectangle) {
        return getRectangleCoordinate(rectangle::getX, rectangle::getWidth, true);
    }

    private double getRectangleRightX(final Rectangle rectangle) {
        return getRectangleCoordinate(rectangle::getX, rectangle::getWidth, false);
    }

    private double getRectangleLowerY(final Rectangle rectangle) {
        return getRectangleCoordinate(rectangle::getY, rectangle::getHeight, true);
    }

    private double getRectangleUpperY(final Rectangle rectangle) {
        return getRectangleCoordinate(rectangle::getY, rectangle::getHeight, false);
    }

    /*The boolean isSignNegative is true for lowerY and leftX, because this method to get those coordinates has to
     * subtract the half of dimension from the center coordinate. Instead, isSignNegative is false for upperY and rightY.
    */
    private double getRectangleCoordinate(final Supplier<Double> coordinateSupplier, final Supplier<Double> dimensionSupplier,
            final boolean isSignNegative) {
        return coordinateSupplier.get() + (isSignNegative ? -1 : +1) * (dimensionSupplier.get() / 2);
    }

    private boolean isBetween(final double num, final double min, final double max) {
        return min < num && num < max;
    }

    private void playerJumps(final Player player, final Platform platform) {
        player.setVelocityFromJump(platform.getJumpVelocity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionHandler<Rectangle, RectangleHitbox, Enemy> createEnemyCollisionHandler() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createEnemyCollisionHandler'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionHandler<Circle, CircleHitbox, Coin> createCoinCollisionHandler() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCoinCollisionHandler'");
    }
}
