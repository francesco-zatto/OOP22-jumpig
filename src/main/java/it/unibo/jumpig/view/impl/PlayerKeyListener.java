package it.unibo.jumpig.view.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.jumpig.common.impl.Direction;

/**
 * Class that manages the input from the keyboard.
 */
public class PlayerKeyListener implements KeyListener {

    private Direction lastInput = Direction.HORIZONTAL_ZERO;

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyTyped(final KeyEvent e) {
        this.lastInput = Direction.HORIZONTAL_ZERO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.lastInput = Direction.HORIZONTAL_SX;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.lastInput = Direction.HORIZONTAL_DX;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        this.lastInput = Direction.HORIZONTAL_ZERO;
    }

    /**
     * Getter for the lastInput.
     * @return the last input
     */
    public Direction getLastInput() {
        return this.lastInput;
    }
}
