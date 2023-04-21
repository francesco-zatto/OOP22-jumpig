package it.unibo.jumpig.model.impl;

import it.unibo.jumpig.model.api.Game;
import it.unibo.jumpig.model.api.World;

/**
 * The class to manage a game.
 */

public class GameImpl implements Game {

    private final World world;

    /**
     * The constructor to create a new game.
     */
    public GameImpl() {
        this.world = new WorldImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOVer() {
        return this.world.getPlayer().getLives() <= 0 
            || this.world.getCamera().getHeight(this.world.getPlayer()).isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGame(final long elapsed) {
        this.world.updateGame(elapsed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentScore() {
        return (int) this.world.getPlayer().getPosition().getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentCoins() {
        return this.world.getPlayer().getCoins();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld() {
        return this.world;
    }
}
