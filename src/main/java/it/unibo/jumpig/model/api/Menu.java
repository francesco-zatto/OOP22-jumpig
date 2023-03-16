package it.unibo.jumpig.model.api;

/**
 * interface Menu.
 */
public interface Menu {
    /**
     * method that starts a new Game.
     */
    void startGame();

    /**
     * getter for the leaderboard.
     * @return the leaderboard
     */
    Leaderboard getLeaderboard();
}
