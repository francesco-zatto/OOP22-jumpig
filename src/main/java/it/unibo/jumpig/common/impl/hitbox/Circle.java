package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.ShapeHitbox;

/**
 * The class to manage the circular area of the Hitbox.
 */

public class Circle implements ShapeHitbox {

    private final Position center = null;

    @Override
    public Position getCenter() {
        return this.center;
    }
}
