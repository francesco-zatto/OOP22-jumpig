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
     * Getter that returns the camera's height, 
     * that is the height from which the world we see starts.
     * @return the camra's height.
     */
    double getCameraHeight();

    /**
     * Setter to update camera's height.
     * @param cameraheight the camera's height.
     */
    void setCameraHeight(int cameraheight);

    /**
     * The method to update the camera's height.
     * @param player the player of the game
     */
    void updateCameraVelocity(Player player);

    /**
     * Method for a defensive copy.
     * @return the camera's copy
     */
    Camera copy();

}
