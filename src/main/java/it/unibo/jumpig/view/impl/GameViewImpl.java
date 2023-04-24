package it.unibo.jumpig.view.impl;

import java.util.Arrays;
import java.util.Set;

import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.Component;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.controller.api.GameController;
import it.unibo.jumpig.view.api.GameViewScene;

/**
 * The class to manage updates of score and updates of the game.
 */

public class GameViewImpl implements GameViewScene {

    public static final long serialVersionUID = 1L;
    private final double width; //NOPMD
    private final double height; //NOPMD
    private final GameController controller; //NOPMD
    private final JPanel mainPanel;

    /**
     * Constructor to create the game view that manage updates.
     * @param width the width of the world
     * @param height the height of the world
     */
    @SuppressFBWarnings(value = "UrF", 
            justification = "I have to initialize controller but it will be set with other constructor")
    public GameViewImpl(
        final double width, 
        final double height
    ) {
            this.height = height;
            this.width = width;
            this.mainPanel = new JPanel();
            this.controller = null;
            this.mainPanel.add(new GamePanel(this.width, this.height));
            this.mainPanel.add(new ScorePanel());
            this.mainPanel.setVisible(false);
    }

    /**
     * Constructor to set the game controller of the game view. 
     * @param gameController the controller of the game
     * @param width the width of the world
     * @param height the height of the world
     * @param gameview the game view to copy
     * */
    public GameViewImpl(
        final GameController gameController, 
        final double width, 
        final double height, 
        final GameViewScene gameview 
        ) {
            this.mainPanel = gameview.getMainPanel();
            this.controller = gameController;
            this.width = width;
            this.height = height;
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
    public int manageInput() {
        //TODO
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderEntities(final Set<Hitbox> entities) {
        Arrays.stream(this.mainPanel.getComponents())
            .forEach(x -> this.refreshEntities(x, entities));
    }

    private void refreshEntities(
        final Component x, 
        final Set<Hitbox> entities
        ) {
            if (x instanceof GamePanel) {
                ((GamePanel) x).refresh(entities);
                x.repaint();
            }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderCurrentScore(
        final int coins, 
        final int height, 
        final int lives
        ) {
            Arrays.stream(this.mainPanel.getComponents())
                .forEach(x -> this.refreshScore(x, coins, height, lives));
    }

    private void refreshScore(
        final Component x, 
        final int coins, 
        final int height, 
        final int lives
        ) {
            if (x instanceof ScorePanel) {
                ((ScorePanel) x).refresh(coins, height, lives);
            }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI",
    justification = "I have to return the mainPanel")
    public JPanel getMainPanel() {
        return this.mainPanel; //NOPMD
    }
}
