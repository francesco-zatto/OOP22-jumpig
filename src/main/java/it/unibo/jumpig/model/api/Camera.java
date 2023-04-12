package it.unibo.jumpig.model.api;

import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Interface Camera.
 */
public interface Camera {
    /**
     * Getter that returns the camera's height.
     * @return a value that represents the camera's height.
     * @param player the player the camera follows. 
     */
    int getHeight(Player player);
}
