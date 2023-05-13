package it.unibo.jumpig.model.api;

import java.util.Optional;

import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Interface Camera.
 */
public interface Camera {

    /**
     * Getter that returns the last platform height.
     * @return a value that represents the camera's height (if it's present).
     * @param player the player the camera follows. 
     */
    Optional<Double> getPlatformHeight(Player player);

    /**
     * Setter for the last platform on which the player has jumped.
     * @param lastPlatform the last platform on which the player has jumped
     */
    void setLastPlatformHeight(Optional<Double> lastPlatform);

    /**
     * Getter that returns the camera's height, 
     * that is the height from which the world we see starts.
     * @return the camra's height.
     */
    double getCameraHeight();

    /**
     * Setter to update camera's height.
     * @param cameraheight the camera's height.
     * @param player the player of the game.
     */
    void setCameraHeight(double cameraheight, Player player);

    /**
     * The method to update the camera's height.
     * @param player the player of the game
     */
    void setCameraVelocity(Player player);

    /**
     * Method for a defensive copy.
     * @param player the player of the game
     * @return the camera's copy
     */
    Camera copy(Player player);

    /**
     * The getter for the height from which the camera starts.
     * It will be a multiple of the HEIGHT of the world. 
     * @return the height from which the camera start
     */
    double getCameraStartHeight();
}
