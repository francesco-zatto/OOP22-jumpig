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
import it.unibo.jumpig.model.api.gameentity.Targettable;

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
        if (platform instanceof Targettable && ((Targettable) platform).isTaken()) {
            return false;
        }
        if (player.getVelocity().getYComponent() >= 0) {
            return false;
        }
        final RectangleHitbox playerHitbox = player.getHitbox();
        final RectangleHitbox platformHitbox = platform.getHitbox();
        final double playerLeftX = playerHitbox.getRectangleLeftX();
        final double playerRightX = playerHitbox.getRectangleRightX();
        final double platformLeftX = platformHitbox.getRectangleLeftX();
        final double platformRightX = platformHitbox.getRectangleRightX();
        final boolean isPlayerAligned = isBetween(playerLeftX, platformLeftX, platformRightX) 
                || isBetween(playerRightX, platformLeftX, platformRightX);
        final boolean isPlayerAbove = isBetween(playerHitbox.getRectangleLowerY(), platformHitbox.getCenter().getY(),
                platformHitbox.getRectangleUpperY());
        return isPlayerAligned && isPlayerAbove;
    }

    private void playerJumps(final Player player, final Platform platform) {
        if (platform instanceof Targettable) {
            ((Targettable) platform).markTarget();
        }
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
        final double playerLeftX = playerHitbox.getRectangleLeftX();
        final double playerRightX = playerHitbox.getRectangleRightX();
        final double enemyLeftX = enemyHitbox.getRectangleLeftX();
        final double enemyRightX = enemyHitbox.getRectangleRightX();
        final boolean isPlayerAligned = isBetween(playerLeftX, enemyLeftX, enemyRightX) 
                || isBetween(playerRightX, enemyLeftX, enemyRightX);
        final double playerLowerY = playerHitbox.getRectangleLowerY();
        final double playerUpperY = playerHitbox.getRectangleUpperY();
        final double enemyLowerY = enemyHitbox.getRectangleLowerY();
        final double enemyUpperY = enemyHitbox.getRectangleUpperY();
        final boolean isPlayerOnTheSameHeight = isBetween(playerLowerY, enemyLowerY, enemyUpperY) 
                || isBetween(playerUpperY, enemyLowerY, enemyUpperY);
        return isPlayerAligned && isPlayerOnTheSameHeight;
    }

    private void playerCollidesWithEnemy(final Player player, final Enemy enemy) {
        player.decreaseLives();
        enemy.markTarget();
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
        final double playerLeftX = playerHitbox.getRectangleLeftX();
        final double playerRightX = playerHitbox.getRectangleRightX();
        final double playerLowerY = playerHitbox.getRectangleLowerY();
        final double playerUpperY = playerHitbox.getRectangleUpperY();
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
        player.incrementCoins();
        coin.markTarget();
    }

    /*This inequality is based on the equation of a circle: (x - xCenter) ^ 2 + (y - yCenter) ^ 2 = radius ^ 2.
     * The equation means that every point on the circumference is distant from the center a length that equals to the radius.
     * If a point is nearest to the center, then the first member of the equation is less than the second member.
     */
    private boolean isPositionInsideCircle(final Position position, final CircleHitbox circle) {
        return Math.pow(position.getX() - circle.getCenter().getX(), 2) - Math.pow(position.getY() - circle.getCenter().getY(), 2)
                < Math.pow(circle.getRadius(), 2);
    }

    private boolean isBetween(final double num, final double min, final double max) {
        return min < num && num < max;
    }
}
