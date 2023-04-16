package it.unibo.jumpig.controller.impl;

import it.unibo.jumpig.controller.api.MenuController;
import it.unibo.jumpig.view.api.MenuViewScene;
import it.unibo.jumpig.view.impl.MenuViewSceneImpl;

/**
 * Class that manages the menu interactions.
 */
public class MenuControllerImpl implements MenuController {

    private final MenuViewScene view = new MenuViewSceneImpl(this);

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.view.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        this.view.quit();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void notifyStartGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notifyStartGame'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyStartLeaderboard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notifyStartLeaderboard'");
    }
}
