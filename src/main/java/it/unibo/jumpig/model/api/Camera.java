package it.unibo.jumpig.model.api;

import java.util.Optional;

import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Interface Camera.
 */
public interface Camera {
    /**
     * Getter that returns the camera's height.
     * @return a value that represents the camera's height (if it's present).
     * @param player the player the camera follows. 
     */
    Optional<Double> getHeight(Player player);
}
