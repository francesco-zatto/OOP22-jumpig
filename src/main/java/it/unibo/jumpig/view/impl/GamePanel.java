package it.unibo.jumpig.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

//import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
//import it.unibo.jumpig.common.impl.PositionImpl;
//import it.unibo.jumpig.model.impl.gameentity.BasicPlatform;

/**
 * The GUI that shows the game currently going on.
 */
public class GamePanel extends JPanel { 

    public static final long serialVersionUID = 1L;
    private final double worldWidth;
    private final double worldHeight;
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension startScreen = new Dimension((int) screen.getWidth() / 5, (int) (screen.getWidth() / 5 * 1.7));

    /**
     * Constructor for a gamePanel.
     * @param platformSet a set of platform for testing
     */
    public GamePanel(final double worldWidth, final double worldHeight) { 
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.setSize(this.startScreen);
        this.setPreferredSize(this.getSize());
    }

    /**
     * Method that paints every drawing of the gamePanel.
     * @param g graphics used to paint.
     */
    @Override
    @SuppressFBWarnings(value = "BC_UNCONFIRMED_CAST",
            justification = "It's a safe and necessary cast because g is Graphics2D")
    public void paint(final Graphics g) {
        final Graphics2D g2D = (Graphics2D) g;
        g2D.setBackground(Color.CYAN);
        super.paint(g2D);
    }

    /*public static void main(String[] args) {
        var frame = new JFrame();
        Platform platform = new BasicPlatform(new PositionImpl(15, 15), 30);
        Platform platform2 = new BasicPlatform(new PositionImpl(28, 56), 40);
        var panel = new GamePanel(Set.of(platform, platform2));
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }*/
}
