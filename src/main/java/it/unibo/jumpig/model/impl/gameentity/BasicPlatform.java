package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.Velocity;
import it.unibo.jumpig.model.api.gameentity.AbstractGameEntity;
import it.unibo.jumpig.model.api.gameentity.Platform;

/**
 * Class that represents a basic platform, that simply causes the player's jump.
 */
public class BasicPlatform extends AbstractGameEntity implements Platform {

    private final Velocity jumpVelocity;
    private final double length = this.getHitbox().getBounds().getWidth();

    /**
     * Constructor for a basic platform.
     * @param position position of the platform in the world.
     * @param hitbox hitbox of the platform.
     * @param jumpVelocity velocity of a player when jumps on the platform. 
    */
    public BasicPlatform(final Position position, final Hitbox hitbox, final Velocity jumpVelocity) {
        //TODO togli hitbox e crea una platformHitbox passando come parametro non Hitbox ma direttamente length
        super(position, hitbox);
        this.jumpVelocity = jumpVelocity;
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
