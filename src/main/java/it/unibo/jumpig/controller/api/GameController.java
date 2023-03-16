package it.unibo.jumpig.controller.api;

/**
 * Interface Game Controller
 */
public interface GameController {
    /**
     * This method should register the input from the keyboard while in game
     */
    void registerInput();

    /**
     * This method should notify any update from the game
     */
    void notifyUpdate();

}
