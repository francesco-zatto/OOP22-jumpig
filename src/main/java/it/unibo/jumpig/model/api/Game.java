package it.unibo.jumpig.model.api;

/**
 * interface Game.
 */
public interface Game {
    /**
     * this method checks if the game is over.
     * @return the game status
     */
    boolean isOVer();

    /**
     * this method updates positions for every entity in a game.
     */
    void updateGame();

    /**
     * this method returns the player's final score.
     * @return the player's score
     */
    int getCurrentScore();
}
