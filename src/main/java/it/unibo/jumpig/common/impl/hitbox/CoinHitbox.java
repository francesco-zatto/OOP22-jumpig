package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;

/**
 * The class that represents a coin's hitbox.
 */

public class CoinHitbox extends CircleHitbox {

    private static final double COIN_RADIUS = 3;

    /**
     * Constructor for the coin's hitbox.
     * 
     * @param center the center of the hitbox.
     */
    public CoinHitbox(final Position center) {
        super(center, COIN_RADIUS);
    }
}
