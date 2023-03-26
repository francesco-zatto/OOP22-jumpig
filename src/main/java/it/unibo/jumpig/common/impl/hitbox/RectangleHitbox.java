package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * The class to manage a rectangular Hitbox.
 */

public class RectangleHitbox implements Hitbox {

    private Position center;
    private final double width;
    private final double height;

    /**
     * The constructor to create a new rectangular Hitbox.
     * @param center  the abscissa and the ordinate of the center of the rectangular Hitbox.
     * @param width  the width of the rectangular Hitbox.
     * @param height  the height of the rectangular Hitbox.
     */

    public RectangleHitbox(final Position center, final double width, final double height) {
        this.center = center;
        this.width = width;
        this.height = height;
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
    public void updateHitBox(final Position center) {
        this.center = center;
    }

}
