package it.unibo.jumpig.view.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.common.impl.hitbox.HitboxComparator;

/**
 * The GUI that shows the game currently going on.
 */
public class GamePanel extends JPanel { 

    public static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = new Color(102, 178, 255);
    private static final Comparator<Hitbox> HITBOX_COMPARATOR = new HitboxComparator();
    @SuppressFBWarnings(
        value = "SE_BAD_FIELD", 
        justification = "GamePanel is not meant to be serialized." 
    ) 
    private SortedSet<Hitbox> entities = new TreeSet<>(HITBOX_COMPARATOR);
    private final transient SwingRenderer renderer;

    /**
     * Constructor for a gamePanel.
     * @param worldWidth width of game's world.
     * @param worldHeight height of game's world.
     */
    public GamePanel(final double worldWidth, final double worldHeight) { 
        this.renderer = new SwingRenderer(worldWidth, worldHeight);
    }

    /**
     * Method that paints every drawing of the gamePanel.
     * @param graphics awt graphics used to paint.
     */
    @Override
    @SuppressFBWarnings(value = "BC_UNCONFIRMED_CAST",
            justification = "It's a safe and necessary cast because g is Graphics2D")
    public void paint(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D) graphics;
        this.clearPreviousFrame(graphics2D);
        this.setNextFrame(graphics2D);
        graphics2D.dispose();
    }

    private void clearPreviousFrame(final Graphics2D graphics) {
        graphics.setBackground(BACKGROUND_COLOR);
        graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void setNextFrame(final Graphics2D graphics) {
        this.renderer.setRatio(this.getWidth(), this.getHeight());
        this.renderer.setGraphics(graphics);
        final Iterator<Hitbox> iterator = this.entities.iterator();
        while (iterator.hasNext()) {
            try {
                iterator.next().updateRendering(this.renderer);
            } catch (ConcurrentModificationException e) {
                continue;
            }
        }
    }

    /**
     * Method that refresh the set of entities to render.
     * @param entities entities to render in the panel.
     */
    public void refresh(final Set<Hitbox> entities) {
        this.entities = new TreeSet<>(HITBOX_COMPARATOR);
        this.entities.addAll(entities);
    }
}
