package it.unibo.jumpig.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import it.unibo.jumpig.controller.api.LeaderboardController;
import it.unibo.jumpig.model.api.Score;
import it.unibo.jumpig.view.api.LeaderboardViewScene;

/**
 * Class that views the leaderboard.
 */
public class LeaderboardViewSceneImpl implements LeaderboardViewScene {

    private static final String FRAME_TITLE = "Leaderboard";
    private static final double ASPECT_RATIO = 16.0 / 9.0;
    private static final double SCREEN_FRACTION = 5;
    private final LeaderboardController controller;
    private final JFrame frame = new JFrame(FRAME_TITLE);
    private final JPanel mainPanel = new JPanel();
    private final List<Score> scores;

    /**
     * Constructor for LeaderboardViewSceneImpl.
     * @param controller the leaderboard controller
     * @param scores the scores to show
     */
    public LeaderboardViewSceneImpl(
        final LeaderboardController controller, 
        final List<Score> scores
    ) {
        /*
         * Default close operation
         */
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        /*
         * Set the controller.
         */
        this.controller = controller;
        this.scores = new LinkedList<>(scores);
        /*
         * Set the layouts.
         */
        frame.setLayout(new BorderLayout());
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        /*
         * Created an exit button.
         */
        final JButton exitButton = new JButton("EXIT");
        frame.getContentPane().add(exitButton, BorderLayout.SOUTH);
        /*
         * Set the frame dimensions.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension startScreen = new Dimension(
            (int) (screen.getWidth() / SCREEN_FRACTION), 
            (int) (screen.getWidth() / SCREEN_FRACTION * ASPECT_RATIO)
            );
        frame.setSize(startScreen);
        frame.setLocationByPlatform(true);
        frame.setPreferredSize(frame.getSize());
        frame.setResizable(true);
        /*
         * Added action listener in exit button.
         */
        exitButton.addActionListener(e -> this.controller.close());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        createLeaderboardOfScores();
        frame.setVisible(true);
    }

    /**
     * Method that add the leaderboard inside the JPanel.
     */
    private void createLeaderboardOfScores() {
        final JTable scores = new JTable(new DefaultTableModel(
            getTableFromScores(), 
            getColumnNames()
        ));
        scores.setBackground(Color.CYAN);
        this.mainPanel.setBackground(Color.PINK);
        this.mainPanel.add(scores);
        this.frame.pack();
    }

    private Object[] getColumnNames() {
        return List.of("Username", "Height", "Coins").toArray();
    }

    private Object[][] getTableFromScores() {
        final List<Object[]> arrayScores = this.scores.stream()
                .map(s -> List.of(s.getUsername(), s.getHeightScore(), s.getCoins()))
                .map(List::toArray)
                .toList();
        Object[][] tableMatrix = new Object[arrayScores.size() + 1][3];
        tableMatrix[0] = getColumnNames();
        for (int i = 0; i < arrayScores.size(); i++) {
            tableMatrix[i + 1] = arrayScores.get(i);
        }
        return tableMatrix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        frame.setVisible(false);
        frame.dispose();
    }
}
