package it.unibo.jumpig.common.api.hitbox;

import it.unibo.jumpig.common.api.Position;

/**
 * The interface to manage the shape of the Hitbox.
 */
public interface ShapeHitbox { 
    /**
     * The method to get the center of the shape.
     * @return the center of the shape.
     */
    Position getCenter();
}
