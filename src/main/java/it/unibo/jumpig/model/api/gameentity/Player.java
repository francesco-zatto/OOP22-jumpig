package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.model.api.Velocity;

/**
 * An interface that represents the player piloted by the user in the game.
 */
public interface Player extends GameEntity {
    /**
     * Getter of the player's lives.
     * @return the player's lives.
     */
    int getLives();

    /**
     * Method that sets the player's velocity after a jump on a platform.
     * @param velocity the velocity given by the platform.
     */
    void setVelocityFromJump(Velocity velocity);

    /**
     * Method that changes the player's velocity because of the world's gravity.
     * @param gravity gravity of the game's world.
     * @param dt the time interval in which to compute the next velocity.
     */
    void computeVelocity(double gravity, double delta);

}
