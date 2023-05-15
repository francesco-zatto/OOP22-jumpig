package it.unibo.jumpig.controller.impl;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import it.unibo.jumpig.common.impl.Direction;
import it.unibo.jumpig.controller.api.GameController;
import it.unibo.jumpig.model.api.Game;
import it.unibo.jumpig.model.impl.GameImpl;
import it.unibo.jumpig.view.api.GameViewScene;
import it.unibo.jumpig.view.impl.GameViewImpl;

/**
 *The class to manage the game loop.
 */

public class GameControllerImpl implements GameController {

    private static final long PERIOD = 20; /* 20 milliseconds are equal to 50 frames per sec */
    private final Game game;
    private final GameViewScene gameView;
    private Direction input;
    private final Logger logger = System.getLogger("GameControllerImpl");

    /**
     * Constructor to create a new Game Controller in order to start a new Game.
     */
    public GameControllerImpl() {
        this.game = new GameImpl();
        this.gameView = new GameViewImpl(
            this.game.getWorld().getWidth(),
            this.game.getWorld().getHeight()
        );
    }

    /**
     * The game loop performing each frame update according to the game loop pattern.
     */
    private void mainLoop() {
        long previousCycleStartTime = System.currentTimeMillis();
        while (!game.isOver() && gameView.isViewActive()) {
            final long currentCycleStartTime = System.currentTimeMillis();
            final long elapsed = currentCycleStartTime - previousCycleStartTime;
            this.registerInput();
            this.game.updateGame(elapsed, this.input);
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
                logger.log(Level.ERROR, "Exception in waiting for next frame");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyUpdate() {
        this.gameView.renderEntities(this.game.getWorld().getEntities());
        this.gameView.renderCurrentScore(
            this.game.getCurrentCoins(), 
            this.game.getCurrentScore(), 
            this.game.getWorld().getPlayer().getLives()
            );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerInput() {
        this.input = this.gameView.manageInput();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        this.gameView.quit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.gameView.show();
        this.mainLoop();
        this.close();
    }
}
