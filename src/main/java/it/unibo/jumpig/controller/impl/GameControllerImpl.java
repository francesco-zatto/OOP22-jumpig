package it.unibo.jumpig.controller.impl;

import it.unibo.jumpig.controller.api.GameController;
import it.unibo.jumpig.model.api.Game;
import it.unibo.jumpig.model.impl.GameImpl;

/**
 *The class to manage the game loop.
 */

public class GameControllerImpl implements GameController {

    private static final long PERIOD = 20; /* 20 milliseconds are equal to 50 frames per sec */
    private final Game game;

    /**
     * Constructor to create a new Game Controller in order to start a new Game.
     */
    public GameControllerImpl() {
        this.game = new GameImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mainLoop() {
        long previousCycleStartTime = System.currentTimeMillis();
        while (!game.isOVer()) {
            final long currentCycleStartTime = System.currentTimeMillis();
            final long elapsed = currentCycleStartTime - previousCycleStartTime;
            registerInput();
            this.game.updateGame(elapsed);
            this.notifyUpdate();
            this.waitForNextFrame(currentCycleStartTime);
            previousCycleStartTime = currentCycleStartTime;
        }
    }

    /**
     * The method to wait the next frame.
     * @param cycleStartTime the current frame.
     */
    private void waitForNextFrame(final long cycleStartTime) {
        final long deltaTime = System.currentTimeMillis() - cycleStartTime;
        if (deltaTime < PERIOD) {
            try {
                Thread.sleep(PERIOD - deltaTime);
            } catch (IllegalArgumentException | InterruptedException ex) {
                return;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyUpdate() {
        // TODO Avrò una view e un game qui dentro e chiamo view.render() e game.update()
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerInput() {
        // TODO Auto-generated method stub 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        // TODO This method will close the game view.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        // TODO This method will show the game view
    }
}
