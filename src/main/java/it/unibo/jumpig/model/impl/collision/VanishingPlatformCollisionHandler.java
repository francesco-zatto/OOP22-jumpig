package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.AbstractCollisionHandler;
import it.unibo.jumpig.model.api.collision.CollisionActioner;
import it.unibo.jumpig.model.api.collision.CollisionChecker;
import it.unibo.jumpig.model.impl.gameentity.VanishingPlatform;

/**
 * Class that handles collisions between a player and a vanishing platform.
 */
public class VanishingPlatformCollisionHandler extends AbstractCollisionHandler<RectangleHitbox, VanishingPlatform> {

    private final BasicPlatformCollisionHandler basicPlatformCollisionHandler = new BasicPlatformCollisionHandler();

    @Override
    protected CollisionChecker<RectangleHitbox, VanishingPlatform> getCollisionChecker() {
        return (player, platform) -> platform.isTaken()
                && this.basicPlatformCollisionHandler.getCollisionChecker().check(player, platform);
    }

    @Override
    protected CollisionActioner<RectangleHitbox, VanishingPlatform> getCollisionActioner() {
        return (player, platform) -> {
            platform.markTarget();
            this.basicPlatformCollisionHandler.getCollisionActioner().act(player, platform);
        };
    }
}
