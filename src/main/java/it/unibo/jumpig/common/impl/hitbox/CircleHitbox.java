package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * The class to manage a circular Hitbox.
 */

public class CircleHitbox implements Hitbox<Circle> {

    private final Circle circle;

    /**
     * The constructor to create a new circular Hitbox.
     * @param center the center of the circular Hitbox.
     * @param radius the radius of the circular Hitbox.
     */
    
    public CircleHitbox(final Position center, final double radius) {
        this.circle = new Circle(center, radius);
    }

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
