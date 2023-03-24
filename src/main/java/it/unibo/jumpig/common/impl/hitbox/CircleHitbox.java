package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * The class to manage a circular Hitbox.
 */

public class CircleHitbox implements Hitbox<Circle> {

    private final Circle circle = null;

    @Override
    public Circle getBounds() {
        return this.circle;
    }

    @Override
    public Circle updateHitBox(Position center) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateHitBox'");
    }
    
}
