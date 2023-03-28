package it.unibo.jumpig.model.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.unibo.jumpig.model.api.Score;
import it.unibo.jumpig.model.api.LeaderboardLoader;

/**
 * class LeaderboardLoaderImpl should order the list of scores and save them
 * in the Leaderboard.txt.
 */

public class LeaderboardLoaderImpl implements LeaderboardLoader {

    /**
     * This contructor order the list of scores by sort.
     * 
     * @return sorted list
     * @param score the list of score whthin need to saved
     */
    public List<Score> sortScores(final List<Score> score) {
        final List<Score> listScores = new ArrayList<>(score);
        Collections.sort(listScores, Collections.reverseOrder());
        return listScores;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveScores(final List<Score> sortedScore) {
        final Logger logger = Logger.getLogger(LeaderboardLoaderImpl.class.getName());

        final File file = new File("./Leaderboard.txt");
        try {
            if (!file.createNewFile()) {
                throw new IOException("Failed to create file");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred in creation of the file ", e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./Leaderboard.txt", StandardCharsets.UTF_8))) {
            final List<Score> listScores = new ArrayList<>(sortedScore);
            for (final Score i : listScores) {
                writer.write(i + "\n");
            }
            writer.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while writing to file ", e);
        }

    }

}
