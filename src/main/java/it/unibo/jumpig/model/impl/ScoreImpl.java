package it.unibo.jumpig.model.impl;

import it.unibo.jumpig.model.api.Score;

/**
 * This class ScoreImpl implement interface Score.
 */
public class ScoreImpl implements Score {

    // set the serialversionUID
    private static final long serialVersionUID = 1L;

    private final String username;
    private final int score;
    private final int coins;

    /**
     * Contructor for the Score.
     * 
     * @param username the username
     * @param score    the score
     * @param coins    the number of the collected coins
     */

    public ScoreImpl(final String username, final int score, final int coins) {
        this.username = username;
        this.score = score;
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
    public int getScore() {
        return this.score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCoins() {
        return this.coins;
    }
}
