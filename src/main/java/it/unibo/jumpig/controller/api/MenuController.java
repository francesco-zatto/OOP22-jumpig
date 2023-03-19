package it.unibo.jumpig.controller.api;

/**
 * Interface Menu Controller
 */

public interface MenuController extends Status {
    /**
     * This method notify when the game starts
     */
    void notifyStartGame();

    /**
     * This method notify when the leaderboard starts
     */
    void notifyStartLeaderboard();

}
