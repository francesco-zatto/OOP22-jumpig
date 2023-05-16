package it.unibo.jumpig.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import it.unibo.jumpig.controller.api.LeaderboardController;
import it.unibo.jumpig.model.api.Leaderboard;
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
    /**
     * Constructor for LeaderboardViewSceneImpl.
     * @param controller the leaderboard controller
     * @param leaderboard the leaderboard
     */
    public LeaderboardViewSceneImpl(
        final LeaderboardController controller,
        final Leaderboard leaderboard
        ) {
        this.controller = controller;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        mainPanel.setLayout(new GridLayout(1, 0));
        createLeaderboardOfScores(mainPanel, leaderboard);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        final JButton exitButton = new JButton("EXIT");
        frame.getContentPane().add(exitButton, BorderLayout.SOUTH);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension startScreen = new Dimension(
            (int) (screen.getWidth() / SCREEN_FRACTION), 
            (int) (screen.getWidth() / SCREEN_FRACTION * ASPECT_RATIO)
            );
        frame.setSize(startScreen);
        frame.setLocationByPlatform(true);
        frame.setPreferredSize(frame.getSize());
        frame.setResizable(true);
        exitButton.addActionListener(e -> this.controller.close());
    }

    /**
     * Method that add the leaderboard inside the JPanel.
     * @param panel the panel where the leaderboard will be added
     * @param leaderboard the leaderboard to visualize
     */
    private void createLeaderboardOfScores(
        final JPanel panel,
        final Leaderboard leaderboard
        ) {
        for (final Score singleScore : leaderboard.getScores()) {
            final JLabel score = new JLabel(
                singleScore.getUsername()
                + "\t"
                + "score:"
                + singleScore.getHeightScore()
                + "\t"
                + "coins: "
                + singleScore.getCoins()
                );
            score.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            panel.add(score);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        frame.dispose();
    }
}
