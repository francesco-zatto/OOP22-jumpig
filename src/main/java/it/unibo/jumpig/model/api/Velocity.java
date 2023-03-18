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
}
