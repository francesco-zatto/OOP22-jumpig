package it.unibo.jumpig.view.impl;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import it.unibo.jumpig.view.api.MenuViewScene;

/**
 * Class that displays the game's main menu.
 */
public class MenuViewSceneImpl implements MenuViewScene {

    private static final String FRAME_TITLE = "Jumpig";
    private final JFrame frame = new JFrame(FRAME_TITLE);
    /**
     * Constructor for the main menu.
     */
    public MenuViewSceneImpl() { 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
