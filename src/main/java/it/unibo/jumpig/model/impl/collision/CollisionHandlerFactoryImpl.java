package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
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
    public CollisionHandler<RectangleHitbox, Platform> createPlatformCollisionHandler() {
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
        final RectangleHitbox playerHitbox = player.getHitbox();
        final RectangleHitbox platformHitbox = platform.getHitbox();
        final double playerLeftX = getRectangleLeftX(playerHitbox);
        final double playerRightX = getRectangleRightX(playerHitbox);
        final double platformLeftX = getRectangleLeftX(platformHitbox);
        final double platformRightX = getRectangleRightX(platformHitbox);
        final boolean isPlayerAligned = isBetween(playerLeftX, platformLeftX, platformRightX) 
                || isBetween(playerRightX, platformLeftX, platformRightX);
        final boolean isPlayerAbove = isBetween(getRectangleLowerY(playerHitbox), platformHitbox.getCenter().getY(),
                getRectangleUpperY(platformHitbox));
        return isPlayerAligned && isPlayerAbove;
    }

    private void playerJumps(final Player player, final Platform platform) {
        player.setVelocityFromJump(platform.getJumpVelocity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionHandler<RectangleHitbox, Enemy> createEnemyCollisionHandler() {
        return new CollisionHandlerImpl<>(this::isPlayerCollidingWithEnemy, this::playerCollidesWithEnemy);
    }

    private boolean isPlayerCollidingWithEnemy(final Player player, final Enemy enemy) {
        return false; //TODO
    }

    private void playerCollidesWithEnemy(final Player player, final Enemy enemy) {
        player.decreaseLives();
        enemy.setTarget(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionHandler<CircleHitbox, Coin> createCoinCollisionHandler() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCoinCollisionHandler'");
    }

    private double getRectangleLeftX(final RectangleHitbox rectangle) {
        return getRectangleCoordinate(rectangle.getCenter().getX(), rectangle.getWidth(), true);
    }

    private double getRectangleRightX(final RectangleHitbox rectangle) {
        return getRectangleCoordinate(rectangle.getCenter().getX(), rectangle.getWidth(), false);
    }

    private double getRectangleLowerY(final RectangleHitbox rectangle) {
        return getRectangleCoordinate(rectangle.getCenter().getY(), rectangle.getHeight(), true);
    }

    private double getRectangleUpperY(final RectangleHitbox rectangle) {
        return getRectangleCoordinate(rectangle.getCenter().getY(), rectangle.getHeight(), false);
    }

    /*The boolean isSignNegative is true for lowerY and leftX, because this method to get those coordinates has to
     * subtract the half of dimension from the center coordinate. Instead, isSignNegative is false for upperY and rightY.
    */
    private double getRectangleCoordinate(final double coordinate, final double dimension, final boolean isSignNegative) {
        return coordinate + (isSignNegative ? -1 : +1) * (dimension / 2);
    }

    private boolean isBetween(final double num, final double min, final double max) {
        return min < num && num < max;
    }
}
