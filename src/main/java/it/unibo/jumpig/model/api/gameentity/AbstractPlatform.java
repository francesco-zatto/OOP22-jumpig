package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.PlatformHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.Velocity;
import it.unibo.jumpig.model.impl.VelocityImpl;
import it.unibo.jumpig.view.api.RenderingComponent;

/**
 * Class to manage the velocity of the jump and the length of a Platform.
 */
public abstract class AbstractPlatform extends AbstractGameEntity<RectangleHitbox> implements Platform {

    private final Velocity jumpVelocity;
    private final double length = this.getHitbox().getWidth();

    /**
     * Constructor for any kind of platform.
     * @param position position of the platform in the world.
     * @param jumpVelocity vertical velocity of a player when jumps on the platform.
     * @param renderingComponent the rendering component of the platform
     */
    protected AbstractPlatform(final Position position, final double jumpVelocity,
            final RenderingComponent<RectangleHitbox> renderingComponent) {
        super(position, new PlatformHitbox(position), renderingComponent);
        this.jumpVelocity = new VelocityImpl(0, jumpVelocity);
    }

    @Override
    public final Velocity getJumpVelocity() {
        return this.jumpVelocity;
    }

    @Override
    public final double getLength() {
        return this.length;
    }
}
