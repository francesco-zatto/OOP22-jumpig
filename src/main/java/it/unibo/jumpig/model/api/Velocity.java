package it.unibo.jumpig.model.api;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.Direction;

/**
 * Interface that represents the velocity
 * in a Cartesian plane of a gameEntity.
 */
public interface Velocity {
    /**
     * Getter for the length of the velocity.
     * @return the length of the velocity vector.
     */
    double getModule();

    /**
     * Getter for the component of the velocity on the x axis.
     * @return the length of the horizontal component.
     */
    double getXComponent();

    /**
     * Getter for the component of the velocity on the y axis.
     * @return the length of the veritcal component.
     */
    double getYComponent();

    /**
     * Method to compute the next position given the time interval 
     * and the initial position.
     * @param initialPosition position where the movement starts.
     * @param deltaTime time interval of the movement.
     * @return the final position after the movement.
     */
    Position computeMovement(Position initialPosition, double deltaTime);

    /**
     * Method to compute the velocity with the gravity acceleration.
     * @param gravity the world's gravity
     * @param deltaTime the time interval of the movement
     */
    void computeAcceleratedVelocity(double gravity, double deltaTime);

    /**
     * Method to compute the horizontal velocity.
     * @param direction the direction the player moves (-1 sx, 1 dx, otherwise 0)
     */
    void computeHorizontalVelocity(Direction direction);
}
