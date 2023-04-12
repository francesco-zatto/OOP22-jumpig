package it.unibo.jumpig.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
//import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.gameentity.Platform;
//import it.unibo.jumpig.model.impl.gameentity.BasicPlatform;

/**
 * The GUI that shows the game currently going on.
 */
public class GamePanel extends JPanel { 

    public static final long serialVersionUID = 1L;
    private static final double WORLD_WIDTH = 36; //tmp costant
    private static final double WORLD_HEIGHT = 64; //tmp costant
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension startScreen = new Dimension((int) screen.getWidth() / 5, (int) (screen.getWidth() / 5 * 1.7));
    //private final double WORLD_WIDTH = 1;
    //private final double WORLD_HEIGHT = 0.5;
    private final Set<Rectangle> platforms = new HashSet<>();

    /**
     * Constructor for a gamePanel.
     * @param platformSet a set of platform for testing
     */
    public GamePanel(final Set<Platform> platformSet) { //TODO SBAGLIATISSIMO
        this.setSize(this.startScreen);
        this.setPreferredSize(super.getSize());
        this.platforms.addAll(platformSet.stream()
                .map(Platform::getHitbox)
                .map(this::convertHitboxToRectangle)
                .collect(Collectors.toSet()));
    }

    private Rectangle convertHitboxToRectangle(final RectangleHitbox hitbox) {
        return new Rectangle(new Point((int) hitbox.getCenter().getX(), (int) hitbox.getCenter().getY()),
                new Dimension((int) (hitbox.getWidth()), (int) (hitbox.getHeight())));
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
        g2D.setColor(Color.RED);
        this.platforms.stream()
                .map(this::scale).forEach(g2D::fill);
    }

    private Rectangle scale(final Rectangle rectangle) {
        return new Rectangle(new Point((int) (rectangle.getCenterX() * this.getWidth() / WORLD_WIDTH),
                (int) (rectangle.getCenterY() * this.getHeight() / WORLD_HEIGHT)),
                new Dimension((int) (rectangle.getWidth() * this.getWidth() / WORLD_WIDTH),
                        (int) (rectangle.getHeight() * this.getHeight() / WORLD_HEIGHT)));

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
