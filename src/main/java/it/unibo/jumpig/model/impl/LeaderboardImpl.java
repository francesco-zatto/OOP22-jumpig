package it.unibo.jumpig.model.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.jumpig.model.api.Leaderboard;
import it.unibo.jumpig.model.api.Score;

public class LeaderboardImpl implements Leaderboard {

    private List<Score> scoreLeaderboard;
   
    /**
     * Constructor for the leaderboard.
     */
    public LeaderboardImpl() {
        this.scoreLeaderboard = new ArrayList<>();
    }

    @Override
    public List<Score> getScores() {
        return this.scoreLeaderboard;
    }

    @Override
    public void addScore(Score score) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addScore'");
    }
    
}
