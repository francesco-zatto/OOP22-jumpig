package it.unibo.jumpig.model.api;

/**
 * Interface Menu.
 */
public interface Menu {
    /**
     * Method that starts a new Game.
     */
    void startGame();

    /**
     * Getter for the leaderboard.
     * @return the leaderboard
     */
    Leaderboard getLeaderboard();
}
