package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.Velocity;

/**
 * An interface that represents the player piloted by the user in the game.
 */
public interface Player extends GameEntity<RectangleHitbox> {
    /**
     * Getter of the player's lives.
     * @return the player's lives.
     */
    int getLives();

    /**
     * Getter for the player's velocity.
     * @return the player's velocity
     */
    Velocity getVelocity();

    /**
     * Method to decrease the player's lives by one unit.
     */
    void decreaseLives();

    /**
     * Method that sets the player's velocity after a jump on a platform.
     * @param velocity the velocity given by the platform.
     */
    void setVelocityFromJump(Velocity velocity);

    /**
     * Method that changes the player's velocity because of the world's gravity.
     * @param gravity gravity of the game's world.
     * @param deltaTime the time interval in which to compute the next velocity.
     */
    void computeVelocity(double gravity, double deltaTime);

    /**
     * Getter for the amount of coins collected by the player.
     * @return the amount of coins
     */
    int getCoins();

    /**
     * Method that increments the amount of coins the player collects during a game.
     */
    void incrementCoins();

    /**
     * Method for a defensive copy.
     * @return the player's copy
     */
    Player copy();

    /**
     * Setter for the last platform's height the player has jumped on.
     * @param lastPlatformHeight the platform's height
     */
    void setLastPlatformHeight(Double lastPlatformHeight);

    /**
     * Getter for the plast platform's height the player has jumped on.
     * @return the last platform's height
     */
    double getLastPlatformHeight();
}
