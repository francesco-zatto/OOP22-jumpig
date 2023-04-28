package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.view.api.Renderer;

/**
 * Class that represents a broken platform's hitbox.
 */
public class BrokenPlatformHitbox extends PlatformHitbox {

    /**
     * Constructor for the hitbox of a BrokenPlatform.
     * @param center center position of the hitbox.
     */
    public BrokenPlatformHitbox(final Position center) {
        super(center);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRendering(final Renderer renderer) {
        renderer.renderBrokenPlatform(this);
    }
}
