package it.unibo.jumpig.model.impl;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.model.api.Velocity;

/**
 * Class VelocityImpl that describes and calculates the entity's velocity.
 */
public class VelocityImpl implements Velocity {

    private final Position position;

    /**
     * Constructor for the velocity.
     * @param position the entity's position inside the game
     */
    public VelocityImpl(final Position position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getModule() {
        return Math.sqrt(Math.pow(getXComponent(), 2) + Math.pow(getYComponent(), 2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getXComponent() {
        return this.position.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getYComponent() {
        return this.position.getY();
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
