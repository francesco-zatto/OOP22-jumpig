package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.ShapeHitbox;

/**
 * The class to manage the circular area of the Hitbox.
 */

public class Circle implements ShapeHitbox {

    private final Position center;
    private final double radius;
    /**
     * Constructor for the Circle.
     * @param center the center of the Circle.
     * @param radius the radius of the Circle.
     */
    public Circle(final Position center, final double radius) {
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
     * The method to get the radius of the Circle.
     * @return a double that is the radius of the Circle.
     */
    public double getRadius() {
        return this.radius;
    }
}
