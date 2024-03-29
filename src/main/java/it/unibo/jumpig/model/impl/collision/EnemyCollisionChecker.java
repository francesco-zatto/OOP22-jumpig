package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.AbstractCollisionChecker;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that checks the collision of the player whith an enemy.
 */
public class EnemyCollisionChecker extends AbstractCollisionChecker<RectangleHitbox, Enemy> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean areBoundsColliding(final Player player, final Enemy gameEntity) {
        final RectangleHitbox playerHitbox = player.getHitbox();
        final RectangleHitbox enemyHitbox = gameEntity.getHitbox();
        return isPlayerAligned(playerHitbox, enemyHitbox) && isPlayerOnTheSameHeight(playerHitbox, enemyHitbox);
    }

    private boolean isPlayerAligned(final RectangleHitbox playerHitbox, final RectangleHitbox enemyHitbox) {
        final double playerLeftX = playerHitbox.getLeftX();
        final double playerRightX = playerHitbox.getRightX();
        final double enemyLeftX = enemyHitbox.getLeftX();
        final double enemyRightX = enemyHitbox.getRightX();
        return super.isBetween(playerLeftX, enemyLeftX, enemyRightX) 
            || super.isBetween(playerRightX, enemyLeftX, enemyRightX);
    }

    private boolean isPlayerOnTheSameHeight(final RectangleHitbox playerHitbox, final RectangleHitbox enemyHitbox) {
        final double playerLowerY = playerHitbox.getLowerY();
        final double playerUpperY = playerHitbox.getUpperY();
        final double enemyLowerY = enemyHitbox.getLowerY();
        final double enemyUpperY = enemyHitbox.getUpperY();
        return isBetween(playerLowerY, enemyLowerY, enemyUpperY) 
            || isBetween(playerUpperY, enemyLowerY, enemyUpperY);
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
    protected boolean canEntityCollide(final Enemy gameEntity) {
        return !gameEntity.isTaken();
    }
}
