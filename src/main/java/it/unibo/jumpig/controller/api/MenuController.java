package it.unibo.jumpig.controller.api;

import it.unibo.jumpig.view.api.GameViewScene;

/**
 * Interface Menu Controller.
 */

public interface MenuController extends Status {
    /**
     * This method notify when the game starts.
     * @param gameview the game view
     */
    void notifyStartGame(GameViewScene gameview);

    /**
     * This method notify when the leaderboard starts.
     */
    void notifyStartLeaderboard();

}
