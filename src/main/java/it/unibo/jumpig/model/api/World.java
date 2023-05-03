package it.unibo.jumpig.model.api;

import java.util.Set;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Interface World.
 */
public interface World {

    /**
     * The method to get the entities' hitbox.
     * @return a set that contains entities' hitbox
     */
    Set<Hitbox> getEntities();

    /**
     * Getter for game gravity.
     * @return the value of the gravity
     */
    double getGravity();

    /**
     * Getter for the player.
     * @return the player
     */
    Player getPlayer();

    /**
     * Getter for the camera.
     * @return the camera.
     */
    Camera getCamera();

    /**
     * The method to get the width of the world.
     * @return the width of the world
     */
    double getWidth();

    /**
     * The method to get the height of the world.
     * @return the height of the world
     */
    double getHeight();

    /**
     * Method that updates the positions for every entity inside the game.
     * @param elapsed the elapsed time.
     * @param direction the player's horizontal direction 
     */
    void updateGame(long elapsed, int direction);
}
