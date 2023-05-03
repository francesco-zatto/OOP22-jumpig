package it.unibo.jumpig.model.impl;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.Velocity;

/**
 * Class VelocityImpl that describes and calculates the entity's velocity.
 */
public class VelocityImpl implements Velocity {

    private final double componentX;
    private double componentY;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void computeAcceleratedVelocity(final double gravity, final double deltaTime) {
        this.componentY = this.componentY + (gravity * deltaTime); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(componentX);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(componentY);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final VelocityImpl other = (VelocityImpl) obj;
        if (Double.doubleToLongBits(this.componentX) != Double.doubleToLongBits(other.componentX)) {
            return false;
        }
        return Double.doubleToLongBits(this.componentY) == Double.doubleToLongBits(other.componentY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void computeHorizontalVelocity(final int direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'computeHorizontalVelocity'");
    }
}
