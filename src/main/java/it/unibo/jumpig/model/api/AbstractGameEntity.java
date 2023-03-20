package it.unibo.jumpig.model.api;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * Class to manage the position and the hitbox of each gameEntity.
*/
public abstract class AbstractGameEntity implements GameEntity {

    private Position position;
    private Hitbox hitbox;

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Hitbox getHitbox() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHitbox'");
    }
    
}
