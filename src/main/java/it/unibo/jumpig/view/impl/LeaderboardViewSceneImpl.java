package it.unibo.jumpig.view.impl;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import it.unibo.jumpig.model.api.Leaderboard;
import it.unibo.jumpig.view.api.LeaderboardViewScene;

/**
 * Class that views the leaderboard.
 */
public class LeaderboardViewSceneImpl implements LeaderboardViewScene {

    private static final String FRAME_TITLE = "Leaderboard";
    private static final double ASPECT_RATIO = 16.0 / 9.0;
    private static final double SCREEN_FRACTION = 5;
    private final JFrame frame = new JFrame(FRAME_TITLE);
    /**
     * Constructor for LeaderboardViewSceneImpl.
     * @param leaderboard the leaderboard
     */
    public LeaderboardViewSceneImpl(final Leaderboard leaderboard) {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        final JLabel leaderboardLabel = new JLabel(leaderboard.toString());
        mainPanel.add(leaderboardLabel);
        frame.getContentPane().add(mainPanel);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension startScreen = new Dimension(
            (int) (screen.getWidth() / SCREEN_FRACTION), 
            (int) (screen.getWidth() / SCREEN_FRACTION * ASPECT_RATIO)
            );
        frame.setSize(startScreen);
        frame.setLocationByPlatform(true);
        frame.setPreferredSize(frame.getSize());
        frame.setResizable(true);
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
