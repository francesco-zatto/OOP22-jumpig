package it.unibo.jumpig.view.api;

/**
 * The interface to show the GameViewScene.
 */

public interface GameViewScene extends ViewScene {

    /**
     * The method to render the entities.
     */
    void renderEntities();

    /**
     * The method to render the current score.
     * @param coins number of coins picked by player
     * @param height height reached by the player
     * @param lives lives of the player
     */
    void renderCurrentScore(int coins, int height, int lives);

}
