package it.unibo.jumpig.model.api.collision;

import it.unibo.jumpig.common.impl.hitbox.Rectangle;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.Platform;

/**
 * Interface that produces any kind of collisionHandler for any kind of
 * collision that can happen in the game.
 */
public interface CollisionHandlerFactory {

    /**
     * Method that creates a collisionHandler for collisions with platforms.
     * @return a collisionHandler to handle collisions with platforms
     */
    CollisionHandler<Rectangle, RectangleHitbox, Platform> createPlatformCollisionHandler();

    /**
     * Method that creates a collisionHandler for collisions with enemies.
     * @return a collisionHandler to handle collisions with enemies
     */
    CollisionHandler<Rectangle, RectangleHitbox, Enemy> createEnemyCollisionHandler();
}
