package it.unibo.jumpig.view.impl;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * The GUI that shows the game currently going on.
 */
public class GamePanel extends JPanel { 

    public static final long serialVersionUID = 1L;

    /**
     * Constructor for a gamePanel.
     */
    public GamePanel() {
        this.setBackground(Color.CYAN);
    }

    /**
     * Method that paints every drawing of the gamePanel.
     * @param g graphics used to paint.
     */
    @Override
    public void paint(final Graphics g) {
        super.paintAll(g);
    }
}
