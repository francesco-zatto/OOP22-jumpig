package it.unibo.jumpig.model.api;

import it.unibo.jumpig.common.impl.Direction;

/**
 * Interface Game.
 */

public interface Game {

    /**
     * Method that checks if the game is over.
     * @return the game status
     */
    boolean isOver();

    /**
     * Method that updates the positions for every entity inside the game.
     * @param elapsed the elapsed time
     * @param direction the player's direction
     */
    void updateGame(long elapsed, Direction direction);

    /**
     * Getter that returns the player's final score.
     * @return the player's score
     */
    int getCurrentScore();

    /**
     * Getter that returns the number of coins collected by the player.
     * @return the number of coins collected by the player.
     */
    int getCurrentCoins();

    /**
     * The getter that returns the world of the game.
     * @return the world of the game.
     */
    World getWorld();

    /**
     * Method that adds the score into the leaderboard once the game is over.
     */
    void addScoreToLeaderboard();
}
