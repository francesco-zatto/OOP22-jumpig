package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.model.api.gameentity.Targettable;

/**
 * Class that represents a platform which, when the player jumps on it, it vanishes.
 */
public class VanishingPlatform extends BasicPlatform implements Targettable {

    private boolean targettable = true;
    /**
     * Constructor for a vanishing platform.
     * @param position position of the platform in the world.
     * @param verticalJumpVelocity vertical velocity of a player when jumps on the platform. 
    */
    public VanishingPlatform(final Position position, final double verticalJumpVelocity) {
        super(position, verticalJumpVelocity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTarget(final boolean setTargettable) {
        this.targettable = setTargettable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTaken() {
        return this.targettable;
    }
}
