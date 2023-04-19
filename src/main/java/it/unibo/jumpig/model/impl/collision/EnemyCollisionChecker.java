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
