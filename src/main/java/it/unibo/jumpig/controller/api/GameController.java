package it.unibo.jumpig.controller.api;

/**
 * Interface Game Controller.
 */
public interface GameController extends Status {
    /**
     * This method register the input from the keyboard while in game.
     */
    void registerInput();

    /**
     * This method notify any update from the game.
     */
    void notifyUpdate();

    /**
     * The game loop performing each frame update according to the game loop pattern.
     */
    void mainLoop();
}
