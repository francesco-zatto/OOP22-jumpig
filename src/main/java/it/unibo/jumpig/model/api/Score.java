package it.unibo.jumpig.model.api;

/**
 * Interface score.
 */
public interface Score {

    /**
     * Getter for the player's username associated to the score.
     * @return a string that represent the username
     */
    String getUsername();

    /**
     * Getter for the score.
     * @return the score
     */
    int getScore();

    /**
     * Getter for the amount of coins collected in a game.
     * @return the amount of coins collected in a game by one player
     */
    int getCoins();
}
