package it.unibo.jumpig.view.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.jumpig.controller.api.GameController;

/**
 * Class that manages the input from the keyboard.
 */
public class PlayerKeyListener implements KeyListener {

    private final GameController controller;
    /**
     * Constructor for the PlayerKeyListener.
     * @param controller the controller 
     */
    public PlayerKeyListener(final GameController controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyTyped(final KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Player si muove a sinistra");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Player si muove a destra");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}
