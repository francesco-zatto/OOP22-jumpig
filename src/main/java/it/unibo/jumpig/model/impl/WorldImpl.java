package it.unibo.jumpig.model.impl;

import java.util.Set;

import it.unibo.jumpig.model.api.World;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * The class to manage the world of the game.
 */

public class WorldImpl implements World {

    private final static double GRAVITY = 9.8;
    private final Player player;

    /**
     * The constructor to create a new world.
     * @param player  the player of the world.
     */
    public WorldImpl(final Player player) {
        this.player = player;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlatform'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return this.player;
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
