package it.unibo.jumpig.model.api;

/**
 * An interface that represents the platform on which the player jumps.
 */
public interface Platform extends GameEntity {
    /**
     * Getter for the velocity that the platform gives to the player
     * when he jumps on it.
     * @return the velocity of the jump on the platform.
     */
    Velocity getJumpVelocity();

    /**
     * Getter for the horizontal length of the platform.
     * @return the length of the platform.
     */
    int getLength();

}
