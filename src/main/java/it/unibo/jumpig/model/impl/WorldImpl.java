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

    /**
     * {@inheritDoc}
     */
    @Override
    public double getGravity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGravity'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayer'");
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
