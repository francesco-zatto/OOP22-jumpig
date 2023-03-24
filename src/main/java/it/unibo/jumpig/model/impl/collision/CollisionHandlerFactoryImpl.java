package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.impl.hitbox.Rectangle;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.CollisionHandler;
import it.unibo.jumpig.model.api.collision.CollisionHandlerFactory;
import it.unibo.jumpig.model.api.gameentity.Platform;

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
        return new CollisionHandlerImpl<>(null, null);
    }
}
