package it.unibo.jumpig.view.impl;

import java.util.Arrays;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.common.impl.Direction;
import it.unibo.jumpig.view.api.GameViewScene;

/**
 * The class to manage updates of score and updates of the game.
 */

public class GameViewImpl implements GameViewScene {

    public static final long serialVersionUID = 1L;
    private static final double ASPECT_RATIO = 16.0 / 9.0;
    private static final double SCREEN_FRACTION = 4;
    private final JFrame frame = new JFrame();
    private final JPanel mainPanel;
    private final PlayerKeyListener playerKeyListener = new PlayerKeyListener();
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension startScreen = new Dimension((int) (screen.getWidth() / SCREEN_FRACTION), 
        (int) (screen.getWidth() / SCREEN_FRACTION * ASPECT_RATIO));

    /**
     * Constructor to create the game view that manage updates.
     * @param width the width of the world
     * @param height the height of the world
     */

    public GameViewImpl(
        final double width, 
        final double height
    ) {
            this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            this.mainPanel = new JPanel(new BorderLayout());
            this.mainPanel.add(new ScorePanel(), BorderLayout.NORTH);
            this.mainPanel.add(new GamePanel(width, height), BorderLayout.CENTER);
            this.frame.add(this.mainPanel);
            this.frame.setSize(this.startScreen);
            this.frame.setLocationByPlatform(false);
            this.frame.setPreferredSize(this.frame.getSize());
            this.frame.setResizable(true);
            this.frame.addKeyListener(playerKeyListener);
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
        this.frame.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction manageInput() {
        return this.playerKeyListener.getLastInput();
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
                x.repaint();
            }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isViewActive() {
        return this.frame.isShowing();
    }

}
