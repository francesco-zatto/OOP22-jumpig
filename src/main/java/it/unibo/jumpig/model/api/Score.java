package it.unibo.jumpig.model.api;

import java.io.Serializable;

/**
 * Interface score.
 */
public interface Score extends Serializable {
    /**
     * Getter for the player's username associated to the score.
     * 
     * @return a string that represent the username
     */
    String getUsername();

    /**
     * Getter for the score, i.e. the maximum height
     * the player has reached in a game.
     * 
     * @return the score
     */
    int getScore();

    /**
     * Getter for the amount of coins collected in a game.
     * 
     * @return the amount of coins collected in a game by one player
     */
    int getCoins();
}
