package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.model.api.gameentity.Targettable;

public class VanishingPlatform extends BasicPlatform implements Targettable {

    public VanishingPlatform(Position position, double verticalJumpVelocity) {
        super(position, verticalJumpVelocity);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void setTarget(boolean setTargettable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTarget'");
    }

    @Override
    public boolean isTaken() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isTaken'");
    }
    
}
