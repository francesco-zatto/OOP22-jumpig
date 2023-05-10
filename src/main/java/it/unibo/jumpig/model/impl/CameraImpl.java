package it.unibo.jumpig.model.impl;

import java.util.Optional;

import it.unibo.jumpig.model.api.Camera;
import it.unibo.jumpig.model.api.Velocity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * The class to manage the camera.
 */

public class CameraImpl implements Camera {

    private int cameraheight;
    private Velocity cameraVelocity;

    /**
     * Constructor to create a new camera.
     * @param cameraheight the camera's height
     */
    public CameraImpl(final int cameraheight) {
        this.cameraheight = cameraheight;
        this.cameraVelocity = new VelocityImpl(0, 0);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCameraVelocity(final Player player) {
       /* if (player.getPosition().getY() >= (HEIGHT / 2 + this.getCameraHeight())) {
            this.setCameraHeight(((int) this.player.getPosition().getY()) - 1);
            // -1 to see the last platform in which the player has jumped
        } */
        this.cameraVelocity = player.getVelocity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Camera copy() {
        return new CameraImpl(this.cameraheight);
    }

}
