package it.unibo.jumpig.model.api;

import java.util.List;

/**
 * Interface Leaderboard loader.
 */
public interface LeaderboardLoader {
    /**
     * This method should take the list of the score and save them in the file.
     * 
     * @param score the list of score
     */
    void saveScores(List<Score> scores);

    /**
     * This method should take the list of the score and save them in the file.
     * 
     * @param file the file that contains leaderboard
     * @return saved score
     */
    List<Score> loadScores();
}
