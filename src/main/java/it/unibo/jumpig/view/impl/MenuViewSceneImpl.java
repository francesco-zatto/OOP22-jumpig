package it.unibo.jumpig.view.impl;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import it.unibo.jumpig.view.api.MenuViewScene;

/**
 * Class that displays the game's main menu.
 */
public class MenuViewSceneImpl implements MenuViewScene {

    private static final String FRAME_TITLE = "Jumpig";
    private final JFrame frame = new JFrame(FRAME_TITLE);
    private final Dimension screen;
    private final int screenWidth;
    private final int screenHeight;
    /**
     * Constructor for the main menu.
     */
    public MenuViewSceneImpl() { 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screen.getWidth();
        screenHeight = (int) screen.getHeight();
        frame.setSize(screenWidth / 2, screenHeight / 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quit'");
    }
}
