package it.unibo.jumpig.model.impl;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.model.api.Velocity;

/**
 * Class VelocityImpl that describes and calculates the entity's velocity.
 */
public class VelocityImpl implements Velocity {

    private final double componentX;
    private final double componentY;

    /**
     * Constructor for the velocity.
     * @param position the entity's position inside the game
     */
    public VelocityImpl(final double componentX, final double componentY) {
        this.componentX = componentX;
        this.componentY = componentY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getModule() {
       // TODO Auto-generated method stub
       throw new UnsupportedOperationException("Unimplemented method 'getModule'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getXComponent() {
        return this.componentX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getYComponent() {
        return this.componentY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position computeMovement(final Position initialPosition, final double deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'computeMovement'");
    }
}
