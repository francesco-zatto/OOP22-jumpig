package it.unibo.jumpig.model.impl;

import java.util.Optional;

import it.unibo.jumpig.model.api.Camera;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * The class to manage the camera.
 */

public class CameraImpl implements Camera {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Double> getHeight(final Player player) {
        return player.getLastPlatformHeight();
    }

}
