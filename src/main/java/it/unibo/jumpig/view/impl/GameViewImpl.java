package it.unibo.jumpig.view.impl;

import java.util.Arrays;
import java.util.Set;

import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.BorderLayout;
import java.awt.Component;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.controller.api.GameController;
import it.unibo.jumpig.view.api.GameViewScene;

/**
 * The class to manage updates of score and updates of the game.
 */

public class GameViewImpl implements GameViewScene {

    public static final long serialVersionUID = 1L;
    private GameController controller; //NOPMD
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
            this.mainPanel = new JPanel(new BorderLayout());
            this.mainPanel.add(new ScorePanel(), BorderLayout.NORTH);
            this.mainPanel.add(new GamePanel(width, height), BorderLayout.SOUTH);
    }

    /**
     * Constructor to set the game controller of the game view. 
     * @param gameController the controller of the game
     * @param gameview the game view to copy
     * */
    public GameViewImpl(
        final GameController gameController, 
        final GameViewImpl gameview 
        ) {
            this.mainPanel = gameview.getMainPanel();
            this.controller = gameController;
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
                ((ScorePanel) x).refresh(height, coins, lives);
            }
    }

    /**
     * The method to get the main panel (which contains score panel and game panel) of the game view.
     * @return the main panel
     */
    @SuppressFBWarnings(value = "EI",
    justification = "I have to return the mainPanel")
    public JPanel getMainPanel() {
        return this.mainPanel; //NOPMD
    }
}
