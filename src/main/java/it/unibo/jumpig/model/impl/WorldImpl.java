package it.unibo.jumpig.model.impl;

import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.jumpig.model.api.World;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * The class to manage the world of the game.
 */

public class WorldImpl implements World {

    private static final double GRAVITY = 9.8;
    private final Player player;
    private final Set<Platform> setplatform;

    /**
     * The constructor to create a new world.
     * @param player  the player of the world.
     * @param setplatform  the set that contains the platforms.
     */
    public WorldImpl(final Player player, final Set<Platform> setplatform) {
        this.player = player.copy();
        this.setplatform = setplatform.stream().collect(Collectors.toSet());
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEnemies'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Coin> getCoins() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCoins'");
    }
}
