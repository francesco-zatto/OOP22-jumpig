package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.PlatformHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.Velocity;
import it.unibo.jumpig.model.api.collision.CollisionHandler;
import it.unibo.jumpig.model.impl.VelocityImpl;

public abstract class AbstractPlatform extends AbstractCollidableGameEntity<RectangleHitbox, Platform> implements Platform {

    private final Velocity jumpVelocity;
    private final double length = this.getHitbox().getWidth();

    public AbstractPlatform(final Position position, final double verticalJumpVelocity,
            final CollisionHandler<RectangleHitbox, Platform> collisionHandler) {
        super(position, new PlatformHitbox(position), collisionHandler);
        this.jumpVelocity = new VelocityImpl(0, verticalJumpVelocity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCollision(final Player player) {
        this.getCollisionHandler().handle(player, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Velocity getJumpVelocity() {
        return this.jumpVelocity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLength() {
        return this.length;
    }
}
