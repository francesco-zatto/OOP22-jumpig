package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.AbstractCollisionHandler;
import it.unibo.jumpig.model.api.collision.CollisionActioner;
import it.unibo.jumpig.model.api.collision.CollisionChecker;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.Player;

public class EnemyCollisionHandler extends AbstractCollisionHandler<RectangleHitbox, Enemy>{

    @Override
    protected CollisionChecker<RectangleHitbox, Enemy> getCollisionChecker() {
        return this::isPlayerCollidingWithEnemy;
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

    @Override
    protected CollisionActioner<RectangleHitbox, Enemy> getCollisionActioner() { 
        return this::playerCollidesWithEnemy;
    }

    private void playerCollidesWithEnemy(final Player player, final Enemy enemy) {
        player.decreaseLives();
        enemy.markTarget();
    }

    
}
