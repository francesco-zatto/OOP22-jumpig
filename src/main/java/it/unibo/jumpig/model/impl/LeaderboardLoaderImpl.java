package it.unibo.jumpig.model.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.jumpig.model.api.Score;
import it.unibo.jumpig.model.api.LeaderboardLoader;

/**
 * This class LeaderboardLoaderImpl should order the list of scores and save.
 */
public class LeaderboardLoaderImpl implements LeaderboardLoader, Serializable {

    /**
     * This method take the correct separator for each different OS.
     */
    public static final String PROP_FILE_SEPARATOR = "file.separator";
    private final String separator = System.getProperty(PROP_FILE_SEPARATOR);

    // set the serialversionUID
    private static final long serialVersionUID = 1L;

    /**
     * This method saves the file countains the score.
     * 
     * @return file path
     */
    public String fileName() {
        return ".." + separator + ".." + separator + ".." + separator + ".." + separator + ".."
                + separator + ".." + separator + "Leaderboard.txt";
    }

    /**
     * This method order the list of scores by sort.
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

        final File file = new File(fileName());
        try {
            if (!file.createNewFile()) {
                throw new IOException("Failed to create file");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred in creation of the file ", e);
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(fileName()))) {
            final List<Score> listScores = new ArrayList<>(sortedScore);
            outputStream.writeObject(listScores);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while writing to file ", e);
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @return scores from leaderboard.txt
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Score> loadScores(final String file) {
        List<Score> scores = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            scores = (List<Score>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Logger.getLogger(LeaderboardLoader.class.getName()).log(Level.SEVERE,
                    "An error occurred while reading from file " + file, e);
        }
        return scores;
    }

}
