package it.unibo.jumpig.model.impl;

import it.unibo.jumpig.model.api.Score;

/**
 * Class ScoreImpl that creates a game's final score.
 */
public class ScoreImpl implements Score {

    private final String username;
    private final double score;
    private final int coins;

    /**
     * Constructor for the score.
     * @param username the player's username
     * @param score the final score, i.e. the maximum height reached
     * @param coins the amount of coins collected in a game
     */
    public ScoreImpl(final String username, final double score, final int coins) {
        this.username = username;
        this.score = score;
        this.coins = coins;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getScore() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getScore'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCoins() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCoins'");
    }
}
