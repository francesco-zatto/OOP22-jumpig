package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * The class to manage a circular Hitbox.
 */

public abstract class CircleHitbox implements Hitbox {

    private Position center;
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
     * The method to get the radius of the circular Hitbox.
     * @return a double that is the radius of the circular Hitbox.
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHitBox(final Position center) {
        this.center = center;
    }
}
