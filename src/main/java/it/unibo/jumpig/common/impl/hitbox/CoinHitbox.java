package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.view.api.Renderer;

/**
 * The class that represents a coin's hitbox.
 */

public class CoinHitbox extends CircleHitbox {

    private static final double COIN_RADIUS = 1.5;

    /**
     * Constructor for the coin's hitbox.
     * @param center the center of the hitbox.
     */
    public CoinHitbox(final Position center) {
        super(center, COIN_RADIUS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRendering(final Renderer renderer) {
        renderer.renderCoin(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLeftX() {
        return super.getCenter().getX() - super.getRadius();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getRightX() {
        return super.getCenter().getX() + super.getRadius();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getUpperY() {
        return super.getCenter().getY() + super.getRadius();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLowerY() {
        return super.getCenter().getY() - super.getRadius();
    }
}
