package it.unibo.jumpig.model.impl;

import it.unibo.jumpig.model.api.Game;
import it.unibo.jumpig.model.api.World;

/**
 * The class to manage a game.
 */

public class GameImpl implements Game {

    private World world;

    /**
     * The constructor to create a new game.
     * @param world  the world of the game.
     */
    public GameImpl(World world) {
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOVer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOVer'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGame'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentScore() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentScore'");
    }
}
