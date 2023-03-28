package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.PlatformHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.Velocity;
import it.unibo.jumpig.model.api.gameentity.AbstractGameEntity;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.impl.VelocityImpl;

/**
 * Class that represents a basic platform, that simply causes the player's jump.
 */
public class BasicPlatform extends AbstractGameEntity<RectangleHitbox> implements Platform {

    private final Velocity jumpVelocity;
    private final double length = this.getHitbox().getWidth();

    /**
     * Constructor for a basic platform.
     * @param position position of the platform in the world.
     * @param verticalJumpVelocity vertical velocity of a player when jumps on the platform. 
    */
    public BasicPlatform(final Position position, final double verticalJumpVelocity) {
        super(position, new PlatformHitbox(position));
        this.jumpVelocity = new VelocityImpl(0, verticalJumpVelocity);
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
