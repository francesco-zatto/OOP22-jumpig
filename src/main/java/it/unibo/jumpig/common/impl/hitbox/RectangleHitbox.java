package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * The class to manage a rectangular Hitbox.
 */

public class RectangleHitbox implements Hitbox<Rectangle> {

    private final Rectangle rectangle;

    public RectangleHitbox(final Position position, final double width, final double height) {
        this.rectangle = new Rectangle(position, width, height);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getBounds() {
        return this.rectangle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle updateHitBox(Position center) {
        // TODO 
        throw new UnsupportedOperationException("Unimplemented method 'updateHitBox'");
    }

}
