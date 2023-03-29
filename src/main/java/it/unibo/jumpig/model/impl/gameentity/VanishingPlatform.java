package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.model.api.gameentity.Targettable;

/**
 * Class that represents a platform which, when the player jumps on it, it vanishes.
 */
public class VanishingPlatform extends BasicPlatform implements Targettable {

    private boolean targettable = true; 

    public VanishingPlatform(final Position position, final double verticalJumpVelocity) {
        super(position, verticalJumpVelocity);
    }

    @Override
    public void setTarget(final boolean setTargettable) {
        this.targettable = setTargettable;
    }

    @Override
    public boolean isTaken() {
        return this.targettable;
    }
    
}
