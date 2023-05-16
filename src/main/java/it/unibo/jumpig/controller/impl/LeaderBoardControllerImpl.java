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
        leaderboardView.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        leaderboardView.quit();
    }
}
