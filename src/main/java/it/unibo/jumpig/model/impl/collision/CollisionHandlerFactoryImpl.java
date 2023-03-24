package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.impl.hitbox.Rectangle;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.CollisionHandler;
import it.unibo.jumpig.model.api.collision.CollisionHandlerFactory;
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
    public CollisionHandler<Rectangle, RectangleHitbox, Platform> createPlatformCollisionHandler() {
        return new CollisionHandlerImpl<>(this::isPlayerJumpingOnPlatform, this::playerJumps);
    }

    private boolean isPlayerJumpingOnPlatform(final Player player, final Platform platform) {
        return player.getPosition().equals(platform.getPosition()); //TO DO
    }

    private void playerJumps(final Player player, final Platform platform) {
        player.setVelocityFromJump(platform.getJumpVelocity());
    }
}
