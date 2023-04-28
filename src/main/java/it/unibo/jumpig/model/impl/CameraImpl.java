package it.unibo.jumpig.model.impl;

import java.util.Optional;

import it.unibo.jumpig.model.api.Camera;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * The class to manage the camera.
 */

public class CameraImpl implements Camera {

    private int cameraheight;

    /**
     * Constructor to create a new camera.
     * @param cameraheight the camera's height
     */
    public CameraImpl(final int cameraheight) {
        this.cameraheight = cameraheight;
    }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCameraHeight(final int cameraheight) {
        this.cameraheight = cameraheight;
    }

}
