package it.unibo.jumpig.model.impl;

import java.util.List;
import java.util.LinkedList;
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

    private static final long serialVersionUID = 1L;
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String PATH_TO_LEADERBOARD = "src" + FILE_SEPARATOR + "main" + FILE_SEPARATOR + "resources" + 
        FILE_SEPARATOR + "it" + FILE_SEPARATOR + "unibo" + FILE_SEPARATOR + "jumpig" + FILE_SEPARATOR;
    private static final String FILE_NAME = PATH_TO_LEADERBOARD + "leaderboard" + FILE_SEPARATOR + "Leaderboard.txt";

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveScores(final List<Score> scores) {
        try (var outputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            outputStream.writeObject(scores);
            outputStream.flush();
        } catch (IOException e) {
            Logger.getLogger(LeaderboardLoaderImpl.class.getName()).log(
                Level.SEVERE,
                "An error occurred while writing to file "
            );
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @return scores from leaderboard.txt
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Score> loadScores() {
        List<Score> scores = new LinkedList<>();
        try (var inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            scores.addAll((List<Score>) inputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            Logger.getLogger(LeaderboardLoader.class.getName()).log(
                Level.SEVERE,
                "An error occurred while reading from file " + FILE_NAME
            );
        }
        return scores;
    }
}
