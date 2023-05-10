package it.unibo.jumpig.model.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jumpig.common.impl.Direction;
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
    public boolean isOver() {
        return this.world.getPlayer().getLives() <= 0 
            || this.world.getCamera().getPlatformHeight(this.world.getPlayer()).isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGame(final long elapsed, final Direction direction) {
        this.world.updateGame(elapsed, direction);
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
    @SuppressFBWarnings(value = "EI", 
                    justification = "it doesn't expose internal representation "
                    + "because world has getters that return copies of objects.")
    public World getWorld() {
        return this.world;
    }
}
