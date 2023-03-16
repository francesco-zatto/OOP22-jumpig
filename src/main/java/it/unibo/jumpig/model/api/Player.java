package it.unibo.jumpig.model.api;

/**
 * An interface that represents the player piloted by the player in the game.
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
    void setVelocityFromJump(int velocity);

}
