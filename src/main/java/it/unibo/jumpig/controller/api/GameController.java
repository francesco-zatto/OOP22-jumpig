package it.unibo.jumpig.controller.api;

/**
 * Interface Game Controller.
 */
public interface GameController extends Status {
    /**
     * This method register the input from the keyboard while in game.
     * @param direction the direction the player moves (-1 sx, 1 dx, otherwise 0)
     */
    void registerInput(int direction);

    /**
     * This method notify any update from the game.
     */
    void notifyUpdate();

}
