package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.view.api.Renderer;

/**
 * Class that represents a vanishing platform's hitbox.
 */
public class VanishingPlatformHitbox extends PlatformHitbox {

    /**
     * Constructor for th hitbox of a VanishingPlatform.
     * @param center center position of the hitbox.
     */
    public VanishingPlatformHitbox(final Position center) {
        super(center);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRendering(final Renderer renderer) {
        renderer.renderVanishingPlatform(this);
    }
}
