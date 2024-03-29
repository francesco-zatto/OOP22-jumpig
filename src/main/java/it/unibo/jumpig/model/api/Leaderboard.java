package it.unibo.jumpig.model.api;

import java.util.List;

/**
 * Interface Leaderboard.
 */
public interface Leaderboard {

    /**
     * Method that loads scores using an internal LeaderboardLoader.
     */
    void loadScores();

    /**
     * Method that returns a list of scores achieved from various players.
     * @return a list of scores
     */
    List<Score> getScores();

    /**
     * Method that inserts a new score inside the leaderboard.
     * @param score the score to add inside the leaderbeard
     */
    void addScore(Score score);

    /**
     * Defensive copy.
     * @return a new copy of the leaderboard
     */
    Leaderboard copy();
}
