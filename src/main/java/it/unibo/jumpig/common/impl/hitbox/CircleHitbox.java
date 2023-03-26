package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * The class to manage a circular Hitbox.
 */

public class CircleHitbox implements Hitbox {

    private final Position center;
    private final double radius;

    /**
     * The constructor to create a new circular Hitbox.
     * @param center the center of the circular Hitbox.
     * @param radius the radius of the circular Hitbox.
     */

    public CircleHitbox(final Position center, final double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getCenter() {
        return this.center;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Circle getBounds() {
        return this.circle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Circle updateHitBox(final Position center) {
        return new Circle(center, this.circle.getRadius());
    }
}
