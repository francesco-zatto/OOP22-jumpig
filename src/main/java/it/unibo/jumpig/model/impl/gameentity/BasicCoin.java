package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.Circle;
import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.model.api.gameentity.AbstractGameEntity;
import it.unibo.jumpig.model.api.gameentity.Coin;

/**
 * The class that represents a basic coin.
 * It has always the same value and once taken by the player it disappears.
 */
public class BasicCoin extends AbstractGameEntity<Circle,CircleHitbox> implements Coin {

    public BasicCoin(Position position, CircleHitbox hitbox) {
        super(position, hitbox);
    }

    @Override
    public Position getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
    public CircleHitbox getHitbox() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHitbox'");
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
