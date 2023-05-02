package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.PlatformHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.CollisionHandler;
import it.unibo.jumpig.model.api.gameentity.AbstractPlatform;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.collision.BasicPlatformCollisionChecker;
import it.unibo.jumpig.model.impl.collision.CollisionHandlerImpl;
import it.unibo.jumpig.model.impl.collision.PlatformCollisionActioner;

/**
 * Class that represents a basic platform, that simply causes the player's jump.
 */
public class BasicPlatform extends AbstractPlatform {

    private final CollisionHandler<RectangleHitbox, BasicPlatform> collisionHandler = new CollisionHandlerImpl<>(
        new PlatformCollisionActioner<>(),
        new BasicPlatformCollisionChecker()
    );
    /**
     * Constructor for a basic platform that simply causes the jump of the player.
     * @param position position of the platform.
     * @param verticalJumpVelocity vertical velocity of a player when jumps on the platform.
     */
    public BasicPlatform(final Position position, final double verticalJumpVelocity) {
        super(position, new PlatformHitbox(position), verticalJumpVelocity);
    }

    @Override
    public final void handleCollision(final Player player) {
        this.collisionHandler.handle(player, this);
    }
}
