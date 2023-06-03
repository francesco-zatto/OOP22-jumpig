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

    /**
     * Constructor for LeaderboardControllerImpl.
     * @param leaderboard the leaderboard
     */
    public LeaderBoardControllerImpl(final Leaderboard leaderboard) {
        this.leaderboardView = new LeaderboardViewSceneImpl(this, leaderboard);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.leaderboardView.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        this.leaderboardView.quit();
    }
}
