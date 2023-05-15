package it.unibo.jumpig.model.impl;

import java.util.Optional;

import it.unibo.jumpig.model.api.Camera;
import it.unibo.jumpig.model.api.Velocity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * The class to manage the camera.
 */

public class CameraImpl implements Camera {

    private int startHeight;
    private double cameraheight;
    private Velocity cameraVelocity;
    private Optional<Double> lastPlatform;

    /**
     * Constructor to create a new camera.
     * @param player the player of the game
     */

    public CameraImpl(final Player player) {
        this.cameraheight = 0;
        this.startHeight = 0;
        this.cameraVelocity = new VelocityImpl(0, 0);
        this.lastPlatform = player.getLastPlatformHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Double> getPlatformHeight(final Player player) {
        return this.lastPlatform;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLastPlatformHeight(final Optional<Double> lastPlatform) {
        this.lastPlatform = lastPlatform;
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
    public void setCameraHeight(final double time, final Player player) {
        if (this.cameraheight < player.getPosition().getY() - 1) {
            this.cameraheight = this.cameraheight + this.cameraVelocity.getYComponent() * time;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCameraVelocity(final Player player) {
        if (this.checkTosetCameraVelocity(player)) {
                this.cameraVelocity = player.getVelocity();
            }
        if (player.getVelocity().getYComponent() < 0) {
            this.cameraVelocity = new VelocityImpl(0, 0);
        }
    }

    private boolean checkTosetCameraVelocity(final Player player) {
        return player.getLastPlatformHeight().isPresent() 
            && (!player.getLastPlatformHeight().get().equals(this.lastPlatform.get()) 
                || this.lastPlatform.isEmpty())
            && player.getVelocity().getYComponent() >= 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Camera copy(final Player player) {
        return new CameraImpl(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCameraStartHeight() {
        return this.startHeight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCameraStartHeight(final int startHeight) {
        this.startHeight = startHeight;
    }

}
