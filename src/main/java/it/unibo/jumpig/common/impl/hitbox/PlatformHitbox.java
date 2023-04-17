package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.view.api.Renderer;

/**
 * Class that represent a platform's hitbox.
 */
public class PlatformHitbox extends RectangleHitbox {

    private static final double PLATFORM_WIDTH = 8; 
    private static final double PLATFORM_HEIGHT = 2;

    /**
     * Constructor for the platform's hitbox.
     * @param center center of the hitbox
    */
    public PlatformHitbox(final Position center) {
        super(center, PLATFORM_WIDTH, PLATFORM_HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRendering(final Renderer renderer) {
        renderer.renderBasicPlatform(this);
    }
}
