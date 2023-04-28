package it.unibo.jumpig.view.impl;

import java.util.Arrays;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.controller.api.GameController;
import it.unibo.jumpig.view.api.GameViewScene;

/**
 * The class to manage updates of score and updates of the game.
 */

public class GameViewImpl implements GameViewScene {

    public static final long serialVersionUID = 1L;
    private GameController controller; //NOPMD
    private final JFrame frame = new JFrame();
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension startScreen = new Dimension((int) screen.getWidth() / 5, 
        (int) (screen.getWidth() / 5 * 1.7));

    /**
     * Constructor to create the game view that manage updates.
     * @param width the width of the world
     * @param height the height of the world
     * @param gameController the controller of the game
     */
    @SuppressFBWarnings(value = "UrF", 
        justification = "Controller will be used to manage input.")
    public GameViewImpl(
        final double width, 
        final double height, 
        final GameController gameController
    ) {
            this.controller = gameController;
            this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.frame.add(new ScorePanel(), BorderLayout.NORTH);
            this.frame.add(new GamePanel(width, height), BorderLayout.SOUTH);
            this.frame.setSize(this.startScreen);
            this.frame.setLocationByPlatform(true);
            this.frame.setPreferredSize(this.frame.getSize());
            this.frame.setResizable(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        this.frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        this.frame.setVisible(false);
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
        Arrays.stream(this.frame.getComponents())
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
            Arrays.stream(this.frame.getComponents())
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
                x.repaint();
            }
    }

}
