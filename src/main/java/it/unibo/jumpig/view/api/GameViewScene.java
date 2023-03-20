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
     */
    void renderCurrentScore();

}
