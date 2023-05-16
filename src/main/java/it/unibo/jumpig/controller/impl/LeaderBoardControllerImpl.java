package it.unibo.jumpig.controller.impl;

import it.unibo.jumpig.controller.api.LeaderboardController;
import it.unibo.jumpig.model.api.Leaderboard;
import it.unibo.jumpig.view.api.LeaderboardViewScene;
import it.unibo.jumpig.view.impl.LeaderboardViewSceneImpl;

/**
 * Class that controls leaderboard interactions.
 */
public class LeaderBoardControllerImpl implements LeaderboardController {

    private final LeaderboardViewScene leaderboardView;
    public LeaderBoardControllerImpl(final Leaderboard leaderboard) {
        this.leaderboardView = new LeaderboardViewSceneImpl(leaderboard);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'close'");
    }
}
