package it.unibo.jumpig.model.impl;

import java.util.Optional;

import it.unibo.jumpig.model.api.Camera;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * The class to manage the camera.
 */

public class CameraImpl implements Camera {

    private final double cameraheight = 1.0; //NOPMD

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Double> getPlatformHeight(final Player player) {
        return player.getLastPlatformHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCameraHeight() {
        return this.cameraheight;
    }
}
