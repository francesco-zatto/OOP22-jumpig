package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
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
        if (enemy.isTaken()) {
            return false;
        }
        final RectangleHitbox playerHitbox = player.getHitbox();
        final RectangleHitbox enemyHitbox = enemy.getHitbox();
        final double playerLeftX = getRectangleLeftX(playerHitbox);
        final double playerRightX = getRectangleRightX(playerHitbox);
        final double enemyLeftX = getRectangleLeftX(enemyHitbox);
        final double enemyRightX = getRectangleRightX(enemyHitbox);
        final boolean isPlayerAligned = isBetween(playerLeftX, enemyLeftX, enemyRightX) 
                || isBetween(playerRightX, enemyLeftX, enemyRightX);
        final double playerLowerY = getRectangleLowerY(playerHitbox);
        final double playerUpperY = getRectangleUpperY(playerHitbox);
        final double enemyLowerY = getRectangleLowerY(enemyHitbox);
        final double enemyUpperY = getRectangleUpperY(enemyHitbox);
        final boolean isPlayerOnTheSameHeight = isBetween(playerLowerY, enemyLowerY, enemyUpperY) 
                || isBetween(playerUpperY, enemyLowerY, enemyUpperY);
        return isPlayerAligned && isPlayerOnTheSameHeight;
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
        return new CollisionHandlerImpl<>(this::isPlayerCollidingWithCoin, this::playerTakesCoin);
    }

    /*To check if the player's hitbox, a rectangle, and the coin's hitbox, a circle, are colliding
     * this method finds the nearest point of the rectangle to the circle's center, named P, and then it checks
     * if P is inside the circle or not, comparing the distance of P from the center to the radius of the circle.
     */
    private boolean isPlayerCollidingWithCoin(final Player player, final Coin coin) {
        if (coin.isTaken()) {
            return false;
        }
        final RectangleHitbox playerHitbox = player.getHitbox();
        final CircleHitbox coinHitbox = coin.getHitbox();
        final Position coinCenter = coinHitbox.getCenter();
        final double playerLeftX = getRectangleLeftX(playerHitbox);
        final double playerRightX = getRectangleRightX(playerHitbox);
        final double playerLowerY = getRectangleLowerY(playerHitbox);
        final double playerUpperY = getRectangleUpperY(playerHitbox);
        final Position nearestPosition;
        final boolean isCoinToTheRight = coinCenter.getX() > playerHitbox.getCenter().getX();
        final boolean isCoinAbove = coinCenter.getY() > playerHitbox.getCenter().getY();
        if (isBetween(coinCenter.getY(), playerLowerY, playerUpperY)) {
            nearestPosition = new PositionImpl(isCoinToTheRight ? playerRightX : playerLeftX, coinCenter.getY());
        } else if (isBetween(coinCenter.getX(), playerLeftX, playerRightX)) {
            nearestPosition = new PositionImpl(coinCenter.getX(), isCoinAbove ? playerUpperY : playerLowerY);
        } else {
            nearestPosition = new PositionImpl(isCoinToTheRight ? playerRightX : playerLeftX, 
                    isCoinAbove ? playerUpperY : playerLowerY);
        }
        return isPositionInsideCircle(nearestPosition, coinHitbox); 
    }

    private void playerTakesCoin(final Player player, final Coin coin) {
        player.decreaseLives(); //TODO correggi -> aggiungi la moneta presa al Player
        coin.setTarget(true);
    }

    /*This inequality is based on the equation of a circle: (x - xCenter) ^ 2 + (y - yCenter) ^ 2 = radius ^ 2.
     * The equation means that every point on the circumference is distant from the center a length that equals to the radius.
     * If a point is nearest to the center, then the first member of the equation is less than the second member.
     */
    private boolean isPositionInsideCircle(final Position position, final CircleHitbox circle) {
        return Math.pow(position.getX() - circle.getCenter().getX(), 2) - Math.pow(position.getY() - circle.getCenter().getY(), 2)
                < Math.pow(circle.getRadius(), 2);
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
