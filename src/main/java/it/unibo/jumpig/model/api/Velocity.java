package it.unibo.jumpig.model.api;

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
}
