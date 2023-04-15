package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;

/**
 * Class that represent a platform's hitbox.
 */
public class PlatformHitbox extends RectangleHitbox {

    private static final double PLATFORM_WIDTH = 6; // TODO
    private static final double PLATFORM_HEIGHT = 2; // TODO

    /**
     * Constructor for the platform's hitbox.
     * 
     * @param center center of the hitbox
     */
    public PlatformHitbox(final Position center) {
        super(center, PLATFORM_WIDTH, PLATFORM_HEIGHT);
    }
}
