package it.unibo.jumpig.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * The GUI that shows the game currently going on.
 */
public class GamePanel extends JPanel { 

    public static final long serialVersionUID = 1L;
    private final double worldWidth;
    private final double worldHeight;
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension startScreen = new Dimension((int) screen.getWidth() / 5, (int) (screen.getWidth() / 5 * 1.7));
    private transient Optional<SwingRenderer> renderer = Optional.empty();

    /**
     * Constructor for a gamePanel.
     * @param worldWidth width of game's world.
     * @param worldHeight height of game's world.
     */
    public GamePanel(final double worldWidth, final double worldHeight) { 
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.setSize(this.startScreen);
        this.setPreferredSize(super.getSize());
        final JPanel scorePanel = new JPanel(new BorderLayout());
        final JPanel componentsPanel = new JPanel(new FlowLayout());
        final JLabel scoreText = new JLabel("Score: ");
        final JLabel scoreNumber = new JLabel("0");
        componentsPanel.add(scoreText);
        componentsPanel.add(scoreNumber);
        componentsPanel.setBackground(Color.CYAN);
        scorePanel.add(componentsPanel, BorderLayout.NORTH);
        this.add(scorePanel);
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
        if (this.renderer.isEmpty()) {
            this.renderer = Optional.of(new SwingRenderer(g2D, this.worldWidth, this.worldHeight,
                    this.getWidth(), this.getHeight()));
        }
        g2D.setBackground(Color.CYAN);
        super.paint(g2D);
        this.renderer.get().setRatio(this.getWidth(), this.getHeight());
    }
    /*
    public static void main(String[] args) {
        var frame = new JFrame();
        Platform platform = new BasicPlatform(new PositionImpl(15, 15), 30);
        Platform platform2 = new BasicPlatform(new PositionImpl(28, 56), 40);
        var panel = new GamePanel(9,16);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    */
}
