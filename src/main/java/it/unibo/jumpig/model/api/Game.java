package it.unibo.jumpig.model.api;

public interface Game {
    
    /**
     * this method checks if the game is over.
     * @return the game status
     */
    boolean isOVer();

    void updateGame();
}
