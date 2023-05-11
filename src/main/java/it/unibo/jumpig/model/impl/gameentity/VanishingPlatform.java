package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.common.impl.hitbox.VanishingPlatformHitbox;
import it.unibo.jumpig.model.api.collision.CollisionHandler;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.api.gameentity.TargettablePlatform;
import it.unibo.jumpig.model.impl.collision.CollisionHandlerImpl;
import it.unibo.jumpig.model.impl.collision.TargettablePlatformCollisionChecker;
import it.unibo.jumpig.model.impl.collision.VanishingPlatformCollisionActioner;

/**
 * Class that represents a platform which, when the player jumps on it, it vanishes.
 */
public class VanishingPlatform extends TargettablePlatform {

    private final CollisionHandler<RectangleHitbox, VanishingPlatform> collisionHandler = new CollisionHandlerImpl<>(
        new VanishingPlatformCollisionActioner(),
        new TargettablePlatformCollisionChecker<VanishingPlatform>()
    );

    /**
     * Constructor for a vanishing platform.
     * @param position position of the platform in the world.
     * @param verticalJumpVelocity vertical velocity of a player when jumps on the platform. 
    */
    public VanishingPlatform(final Position position, final double verticalJumpVelocity) {
        super(position, new VanishingPlatformHitbox(position), verticalJumpVelocity);
    }

    @Override
    public final void handleCollision(final Player player) {
        this.collisionHandler.handle(player, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RectangleHitbox createScaledHitbox(final Position position) {
        return new VanishingPlatformHitbox(position);
    }
}
