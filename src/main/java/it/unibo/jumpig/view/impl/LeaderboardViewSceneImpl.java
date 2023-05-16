package it.unibo.jumpig.view.impl;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import it.unibo.jumpig.model.api.Leaderboard;
import it.unibo.jumpig.view.api.LeaderboardViewScene;

public class LeaderboardViewSceneImpl implements LeaderboardViewScene {

    private static final String FRAME_TITLE = "Leaderboard";
    private static final double ASPECT_RATIO = 16.0 / 9.0;
    private static final double SCREEN_FRACTION = 5;
    private final JFrame frame = new JFrame(FRAME_TITLE);
    private final Leaderboard leaderboard;
    public LeaderboardViewSceneImpl(final Leaderboard leaderboard) {
        this.leaderboard = leaderboard.copy();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
    @Override
    public void show() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }

    @Override
    public void quit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quit'");
    }
    
}
