package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.PlatformHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.Velocity;
import it.unibo.jumpig.model.api.gameentity.AbstractGameEntity;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.VelocityImpl;
import it.unibo.jumpig.model.impl.collision.BasicPlatformCollisionHandler;
import it.unibo.jumpig.view.impl.BasicPlatformRenderingComponent;

/**
 * Class that represents a basic platform, that simply causes the player's jump.
 */
public class BasicPlatform extends AbstractGameEntity<RectangleHitbox> implements Platform {

    private final Velocity jumpVelocity;
    private final double length = this.getHitbox().getWidth();
    private final BasicPlatformCollisionHandler collisionHandler;

    /**
     * Constructor for a basic platform that simply causes the jump of the player.
     * @param position position of the platform.
     * @param verticalJumpVelocity vertical velocity of a player when jumps on the platform.
     */
    public BasicPlatform(final Position position, final double verticalJumpVelocity) {
        super(position, new PlatformHitbox(position), new BasicPlatformRenderingComponent());
        this.jumpVelocity = new VelocityImpl(0, verticalJumpVelocity);
        this.collisionHandler = new BasicPlatformCollisionHandler();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCollision(final Player player) {
        this.collisionHandler.handle(player, this);
    }
}
