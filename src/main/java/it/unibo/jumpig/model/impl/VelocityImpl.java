package it.unibo.jumpig.model.impl;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.Velocity;

/**
 * Class VelocityImpl that describes and calculates the entity's velocity.
 */
public class VelocityImpl implements Velocity {

    private final double componentX;
    private final double componentY;

    /**
     * Contructor for the velocity.
     * @param componentX the X component of the vector velocity
     * @param componentY the Y component of the vector velocity
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
       return Math.sqrt(Math.pow(this.componentX, 2) + Math.pow(this.componentY, 2));
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
        return new PositionImpl(initialPosition.getX() + this.componentX * deltaTime, 
        initialPosition.getY() + this.componentY * deltaTime);
    }

    @Override
    public void computeAcceleratedVelocity(double gravity, double deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'computeAcceleratedVelocity'");
    }
}
