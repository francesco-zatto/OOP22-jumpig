package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.impl.hitbox.Rectangle;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.Velocity;

/**
 * An interface that represents the player piloted by the user in the game.
 */
public interface Player extends GameEntity<Rectangle, RectangleHitbox> {
    /**
     * Getter of the player's lives.
     * @return the player's lives.
     */
    int getLives();

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

}
