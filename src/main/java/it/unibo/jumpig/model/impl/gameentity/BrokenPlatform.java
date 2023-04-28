package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.BrokenPlatformHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.CollisionHandler;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.api.gameentity.TargettablePlatform;
import it.unibo.jumpig.model.impl.collision.BrokenPlatformCollisionActioner;
import it.unibo.jumpig.model.impl.collision.CollisionHandlerImpl;
import it.unibo.jumpig.model.impl.collision.TargettablePlatformCollisionChecker;

/**
 * Class that represents a broken platform, which, when the player jumps on it, it does not give any velocity to the
 * player. Instead it will be entirely broken and will be not available.
 */
public class BrokenPlatform extends TargettablePlatform {

    private final CollisionHandler<RectangleHitbox, BrokenPlatform> collisionHandler = new CollisionHandlerImpl<>(
        new BrokenPlatformCollisionActioner(), 
        new TargettablePlatformCollisionChecker<BrokenPlatform>()
    );

    /**
     * Constructor for a broken platform.
     * @param position position of the platform in the game's world
     */
    protected BrokenPlatform(final Position position) {
        super(position, new BrokenPlatformHitbox(position), 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCollision(final Player player) {
        this.collisionHandler.handle(player, this);
    } 
}
