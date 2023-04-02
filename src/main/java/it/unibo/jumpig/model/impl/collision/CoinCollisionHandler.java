package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.model.api.collision.AbstractCollisionHandler;
import it.unibo.jumpig.model.api.collision.CollisionActioner;
import it.unibo.jumpig.model.api.collision.CollisionChecker;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that handles collisions between a player and a coin.
 */
public class CoinCollisionHandler extends AbstractCollisionHandler<CircleHitbox, Coin> {

    @Override
    protected CollisionChecker<CircleHitbox, Coin> getCollisionChecker() {
        return this::isPlayerCollidingWithCoin;
    }

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

    /*This inequality is based on the equation of a circle: (x - xCenter) ^ 2 + (y - yCenter) ^ 2 = radius ^ 2.
     * The equation means that every point on the circumference is distant from the center a length that equals to the radius.
     * If a point is nearest to the center, then the first member of the equation is less than the second member.
     */
    private boolean isPositionInsideCircle(final Position position, final CircleHitbox circle) {
        return Math.pow(position.getX() - circle.getCenter().getX(), 2) - Math.pow(position.getY() - circle.getCenter().getY(), 2)
                < Math.pow(circle.getRadius(), 2);
    }

    @Override
    protected CollisionActioner<CircleHitbox, Coin> getCollisionActioner() {
        return this::playerTakesCoin;
    }

    private void playerTakesCoin(final Player player, final Coin coin) {
        player.incrementCoins();
        coin.markTarget();
    }
    
}
