package it.unibo.jumpig.model.api;

import java.util.List;

/**
 * interface Leaderboard.
 */
public interface Leaderboard {
    /**
     * this method returns a list of scores achieved from various players.
     * @return a list of scores
     */
    List<Integer> getScores();
}
