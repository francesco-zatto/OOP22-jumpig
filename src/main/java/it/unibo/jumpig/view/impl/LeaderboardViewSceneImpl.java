package it.unibo.jumpig.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    private static final int FONT_SIZE = 12;
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
        /*
         * Set the controller.
         */
        this.controller = controller;
        /*
         * Set the layouts.
         */
        frame.setLayout(new BorderLayout());
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        /*
         * Call to method that creates the leaderboard.
         */
        createLeaderboardOfScores(mainPanel, leaderboard);
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
     * Method that add the leaderboard inside the JPanel.
     * @param panel the panel where the leaderboard will be added
     * @param leaderboard the leaderboard to visualize
     */
    private void createLeaderboardOfScores(
        final JPanel panel,
        final Leaderboard leaderboard
        ) {
        for (final Score singleScore : leaderboard.getScores()) {
            final JLabel score = new JLabel(singleScore.toString());
            score.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            score.setFont(new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE));
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
        frame.setVisible(false);
        frame.dispose();
    }
}
