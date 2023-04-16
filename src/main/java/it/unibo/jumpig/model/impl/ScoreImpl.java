package it.unibo.jumpig.model.impl;

import it.unibo.jumpig.model.api.Score;

/**
 * Class ScoreImpl that creates a game's final score.
 */
public class ScoreImpl implements Score {

    private static final long serialVersionUID = 1L;
    private final String username;
    private final double heightScore;
    private final int coins;

    /**
     * Constructor for the score.
     * @param username the player's username
     * @param heightScore the final score, i.e. the maximum height reached
     * @param coins the amount of coins collected in a game
     */
    public ScoreImpl(final String username, final double heightScore, final int coins) {
        this.username = username;
        this.heightScore = heightScore;
        this.coins = coins;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeightScore() {
        return this.heightScore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCoins() {
        return this.coins;
    }
}
