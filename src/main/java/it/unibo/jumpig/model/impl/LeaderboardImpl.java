package it.unibo.jumpig.model.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.jumpig.model.api.Leaderboard;
import it.unibo.jumpig.model.api.Score;

/**
 * Class that creates the leaderboard for the game.
 */
public class LeaderboardImpl implements Leaderboard {

    private final List<Score> scoreLeaderboard;
    /**
     * Constructor for the leaderboard.
     */
    public LeaderboardImpl() {
        this.scoreLeaderboard = new ArrayList<>();
    }

    /**
     * Private constructor for defensive copy.
     * @param scoreLeaderboard list of scores
     */
    private LeaderboardImpl(final List<Score> scoreLeaderboard) {
        this.scoreLeaderboard = scoreLeaderboard;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Score> getScores() {
        return this.scoreLeaderboard.stream().toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addScore(final Score score) {
        this.scoreLeaderboard.add(score);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder s = new StringBuilder(26);
        for (final Score score : scoreLeaderboard) {
            s.append(
                "User: " 
                + score.getUsername() 
                + "\t" 
                + "Score: " 
                + score.getHeightScore() 
                + "\t" 
                + "Coins: " 
                + score.getCoins() 
                + "\n"
                );
        }
        return s.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Leaderboard copy() {
        return new LeaderboardImpl(this.scoreLeaderboard);
    }
}
