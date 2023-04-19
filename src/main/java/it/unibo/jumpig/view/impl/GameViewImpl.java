package it.unibo.jumpig.view.impl;

import java.util.Set;

import javax.swing.JPanel;
import java.awt.event.KeyEvent;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.controller.api.GameController;
import it.unibo.jumpig.view.api.GameViewScene;

/**
 * The class to manage updates of score and updates of the game.
 */

public class GameViewImpl implements GameViewScene {

    public static final long serialVersionUID = 1L; 
    private final GameController controller;
    private final JPanel mainPanel = new JPanel();

    /**
     * Constructor to create the game view that manage updates. 
     * @param gameController the controller of the game
     * */
    public GameViewImpl(final GameController gameController) {
        this.controller = gameController;
        this.mainPanel.add(new GamePanel(1, 1));
        this.mainPanel.add(new ScorePanel());
        this.mainPanel.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        this.mainPanel.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        this.mainPanel.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void manageInput(final KeyEvent input) {
        //TODO
        this.controller.registerInput();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderEntities(final Set<Hitbox> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderEntities'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderCurrentScore(final int coins, final int height, final int lives) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderCurrentScore'");
    }

}
