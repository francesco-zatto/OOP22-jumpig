package it.unibo.jumpig.model.impl;

import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.jumpig.model.api.Camera;
import it.unibo.jumpig.model.api.GeneratorEntities;
import it.unibo.jumpig.model.api.World;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.api.gameentity.Targettable;
import it.unibo.jumpig.model.impl.gameentity.PlayerImpl;
import it.unibo.jumpig.model.impl.gameentity.VanishingPlatform;

/**
 * The class to manage the world of the game.
 */

public class WorldImpl implements World {

    private static final double GRAVITY = 9.8;
    private final GeneratorEntities generator; //NOPMD perchè lo useremo in updateGame
    private final Player player;
    private final Set<Platform> setplatform;
    private final Set<Enemy> setenemies;
    private final Set<Coin> setcoins;
    private final Camera camera;

    /**
     * The constructor to create a new world.
     */

    public WorldImpl() {
        this.generator = new GeneratorEntitiesImpl();
        this.player = new PlayerImpl(null);
        this.setplatform = generator.generatePlatforms();
        this.setenemies = generator.generateEnemies();
        this.setcoins = generator.generateCoins();
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
        return this.setplatform.stream()
            .filter(x -> x.getClass() == VanishingPlatform.class && !((Targettable) x).isTaken() 
                || x.getClass() != VanishingPlatform.class)
            .collect(Collectors.toSet());
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
        return this.setenemies.stream()
            .filter(x -> !x.isTaken())
            .collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Coin> getCoins() {
       return this.setcoins.stream()
            .filter(x -> !x.isTaken())
            .collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Camera getCamera() {
        return this.camera;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGame(final long elapsed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGame'");
    }
}
