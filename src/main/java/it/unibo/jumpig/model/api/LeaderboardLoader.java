package it.unibo.jumpig.model.api;

import java.util.List;

/**
 * Interface Leaderboard loader.
 */
public interface LeaderboardLoader {
    /**
     * Method that saves the passed scores as argument.
     * @param scores the list of score
     */
    void saveScores(List<Score> scores);

    /**
     * Method that gets the list of scores saved.
     * @return list of scores.
     */
    List<Score> loadScores();
}
