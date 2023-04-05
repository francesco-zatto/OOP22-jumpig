package it.unibo.jumpig.model.api;

/**
 * Interface Game.
 */

public interface Game {

    /**
     * Method that checks if the game is over.
     * @return the game status
     */
    boolean isOVer();

    /**
     * Method that updates the positions for every entity inside the game.
     */
    void updateGame();

    /**
     * Getter that returns the player's final score.
     * @return the player's score
     */
    int getCurrentScore();
}
