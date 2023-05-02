package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.PlatformHitbox;

/**
 * Abstract class extended by any class that is a Platform and is Targettable.
 */
public abstract class TargettablePlatform extends AbstractPlatform implements Targettable {

    private boolean targettable;

    /**
     * Constructor for a TargettablePlatform.
     * @param position position of the platform in the game's world
     * @param hitbox hitbox of the platform
     * @param jumpVelocity velocity of a player when jumps on the platform
     */
    protected TargettablePlatform(final Position position, final PlatformHitbox hitbox, final double jumpVelocity) {
        super(position, hitbox, jumpVelocity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isTaken() {
        return this.targettable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void markTarget() {
        this.targettable = true;
    }
}
