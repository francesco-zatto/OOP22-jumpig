package it.unibo.jumpig.model.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.jumpig.model.api.Leaderboard;
import it.unibo.jumpig.model.api.Score;

/**
 * Class that creates the leaderboard for the game.
 */
public class LeaderboardImpl implements Leaderboard {

    private List<Score> scoreLeaderboard;
    /**
     * Constructor for the leaderboard.
     */
    public LeaderboardImpl() {
        this.scoreLeaderboard = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Score> getScores() {
        return this.scoreLeaderboard;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addScore(Score score) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addScore'");
    }
}
