package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.AbstractCollisionChecker;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that checks the collision of the player with a coin.
 */
public class CoinCollisionChecker extends AbstractCollisionChecker<CircleHitbox, Coin> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean areBoundsColliding(final Player player, final Coin gameEntity) {
        final RectangleHitbox playerHitbox = player.getHitbox();
        final CircleHitbox coinHitbox = gameEntity.getHitbox();
        final Position coinCenter = coinHitbox.getCenter();
        final double playerLeftX = playerHitbox.getLeftX();
        final double playerRightX = playerHitbox.getRightX();
        final double playerLowerY = playerHitbox.getLowerY();
        final double playerUpperY = playerHitbox.getUpperY();
        final Position nearestPosition;
        final double nearestX = getNearestRectangleX(playerHitbox, coinCenter);
        final double nearestY = getNearestRectangleY(playerHitbox, coinCenter);
        /*
         * If the coinY is in the range [playerLowerY, playerUpperY] the nearestPosition is on one
         * of the two vertical lines of the rectangle, with posY = coinY.
         */
        if (isBetween(coinCenter.getY(), playerLowerY, playerUpperY)) {
            nearestPosition = new PositionImpl(nearestX, coinCenter.getY());
        /*
         * If the coinX is in the range [playerLeftX, playerRightX] the nearestPosition is on one
         * of the two horizontal lines of the rectangle, with posX = coinX.
         */
        } else if (isBetween(coinCenter.getX(), playerLeftX, playerRightX)) {
            nearestPosition = new PositionImpl(coinCenter.getX(), nearestY);
        /*
         * If the coin coordinates are not in one of the two ranges checked before the nearestPosition
         * is the nearest vertix of the rectangle.
         */
        } else {
            nearestPosition = new PositionImpl(nearestX, nearestY);
        }
        return isPositionInsideCircle(nearestPosition, coinHitbox);
    }

    private double getNearestRectangleX(final RectangleHitbox playerHitbox, final Position coinCenter) {
        return coinCenter.getX() > playerHitbox.getCenter().getX() 
            ? playerHitbox.getRightX()
            : playerHitbox.getLeftX();
    }

    private double getNearestRectangleY(final RectangleHitbox playerHitbox, final Position coinCenter) {
        return coinCenter.getY() > playerHitbox.getCenter().getY() 
            ? playerHitbox.getUpperY() 
            : playerHitbox.getLowerY();
    }

    /*
     * This inequality is based on the equation of a circumference: (x - xCenter) ^ 2 + (y - yCenter) ^ 2 = radius ^ 2.
     * The equation means that every point on the circumference is distant from the center a length that equals to the radius.
     * If a point is nearest to the center, then the first member of the equation is less than the second member.
     */
    private boolean isPositionInsideCircle(final Position position, final CircleHitbox circle) {
        return Math.pow(position.getX() - circle.getCenter().getX(), 2)
            + Math.pow(position.getY() - circle.getCenter().getY(), 2)
            < Math.pow(circle.getRadius(), 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean canPlayerCollide(final Player player) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean canEntityCollide(final Coin gameEntity) {
        return !gameEntity.isTaken();
    }
}
