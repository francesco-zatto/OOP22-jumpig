package it.unibo.jumpig.controller.impl;

import it.unibo.jumpig.controller.api.GameController;
import it.unibo.jumpig.controller.api.LeaderboardController;
import it.unibo.jumpig.controller.api.MenuController;
import it.unibo.jumpig.model.api.Leaderboard;
import it.unibo.jumpig.model.impl.LeaderboardImpl;
import it.unibo.jumpig.view.api.MenuViewScene;
import it.unibo.jumpig.view.impl.MenuViewSceneImpl;

/**
 * Class that manages the menu interactions.
 */
public class MenuControllerImpl implements MenuController {

    private final MenuViewScene menuView = new MenuViewSceneImpl(this);
    private final Leaderboard leaderboard = new LeaderboardImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.menuView.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        this.menuView.quit();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void notifyStartGame() {
        final GameController gameController = new GameControllerImpl(this.leaderboard, menuView.getUsername());
        gameController.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyStartLeaderboard() {
        final LeaderboardController leaderboardController = new LeaderBoardControllerImpl(this.leaderboard);
        leaderboardController.start();
    }
}
