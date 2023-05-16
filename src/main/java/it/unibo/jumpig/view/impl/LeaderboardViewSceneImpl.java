package it.unibo.jumpig.view.impl;

import javax.swing.JFrame;

import it.unibo.jumpig.model.api.Leaderboard;
import it.unibo.jumpig.view.api.LeaderboardViewScene;

public class LeaderboardViewSceneImpl implements LeaderboardViewScene {

    private static final String FRAME_TITLE = "Leaderboard";
    private final JFrame frame = new JFrame(FRAME_TITLE);
    private final Leaderboard leaderboard;
    public LeaderboardViewSceneImpl(final Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
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
