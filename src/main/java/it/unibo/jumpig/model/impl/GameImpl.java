package it.unibo.jumpig.model.impl;

import java.util.function.Predicate;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jumpig.common.impl.Direction;
import it.unibo.jumpig.model.api.Game;
import it.unibo.jumpig.model.api.World;

/**
 * The class to manage a game.
 */

public class GameImpl implements Game {

    private final World world;
    private double heightScore;
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
            || this.world.getCamera().getPlatformHeight(this.world.getPlayer()).isEmpty() 
                && this.world.getPlayer().getVelocity().getYComponent() < 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGame(final long elapsed, final Direction direction) {
        this.world.updateGame(elapsed, direction);
        manageScoreUpdate(x -> x < this.getWorld().getPlayer().getPosition().getY(), heightScore);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentScore() {
        return (int) this.heightScore;
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

    /**
     * Method that checks if the heightScore needs an update.
     * @param predicate the condition that triggers the update
     * @param score the score that needs an update
     */
    private void manageScoreUpdate(final Predicate<Double> predicate, final double score) {
        if (predicate.test(score)) {
           updateScore();
        }
    }

    /**
     * Method that updates the heightScore.
     */
    private void updateScore() {
        this.heightScore = this.getWorld().getPlayer().getPosition().getY();
    }
}
