package it.unibo.jumpig.model.impl;

import java.util.Random;
import java.util.function.Predicate;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jumpig.common.impl.Direction;
import it.unibo.jumpig.model.api.Game;
import it.unibo.jumpig.model.api.Leaderboard;
import it.unibo.jumpig.model.api.World;

/**
 * The class to manage a game.
 */

public class GameImpl implements Game {

    private static final Random RANDOM = new Random();
    private final World world;
    private int heightScore;
    private final Leaderboard leaderboard;
    private String username;
    /**
     * The constructor to create a new game.
     * @param leaderboard the leaderboard
     * @param username the username
     */
    public GameImpl(
        final Leaderboard leaderboard,
        final String username
        ) {
        this.world = new WorldImpl();
        this.leaderboard = leaderboard.copy();
        this.username = username;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.checkGameOver();
    }

    private boolean checkGameOver() {
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
    private void manageScoreUpdate(final Predicate<Integer> predicate, final int score) {
        if (predicate.test(score)) {
           updateScore();
        }
    }

    /**
     * Method that updates the heightScore.
     */
    private void updateScore() {
        this.heightScore = (int) this.getWorld().getPlayer().getPosition().getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addScoreToLeaderboard() {
        if ("".equals(username)) {
            final var random = RANDOM.nextInt(0, Integer.MAX_VALUE);
            username = "user" + random;
        }
        leaderboard.addScore(new ScoreImpl(username, getCurrentScore(), getCurrentCoins()));
    }
}
