package it.unibo.jumpig.model.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.jumpig.model.api.Camera;
import it.unibo.jumpig.model.api.World;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.gameentity.PlayerImpl;

/**
 * The class to manage the world of the game.
 */

public class WorldImpl implements World {

    private static final double GRAVITY = 9.8;
    private final Player player;
    private final Set<Platform> setplatform;
    private final Set<Enemy> setenemies;
    private final Set<Coin> setcoins;
    private final Camera camera;

    /**
     * The constructor to create a new world.
     */

    public WorldImpl() {
        this.player = new PlayerImpl(null);
        this.setplatform = new HashSet<>();
        this.setenemies = new HashSet<>();
        this.setcoins = new HashSet<>();
        this.camera = new CameraImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getGravity() {
        return GRAVITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Platform> getPlatform() {
        return this.setplatform.stream().collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return this.player.copy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Enemy> getEnemies() {
        return this.setenemies.stream().collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Coin> getCoins() {
       return this.setcoins.stream().collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Camera getCamera() {
        return this.camera;
    }
}
