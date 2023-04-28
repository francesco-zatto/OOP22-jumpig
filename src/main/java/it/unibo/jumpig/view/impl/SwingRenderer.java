package it.unibo.jumpig.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.view.api.Renderer;

/**
 * Class the uses Java Swing library to render the gameEntity.
 */
public final class SwingRenderer implements Renderer {

    private Optional<Graphics2D> graphics = Optional.empty();
    private final double worldWidth;
    private final double worldHeight;
    private double widthRatio;
    private double heightRatio;

    /**
     * Constructor for a SwingRenderer.
     * @param worldWidth width of the game's world.
     * @param worldHeight height of the game's world.
     */
    public SwingRenderer(final double worldWidth, final double worldHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight; 
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderPlayer(final RectangleHitbox entity) {
        graphics.get().setColor(Color.PINK);
        graphics.get().fill(this.createScaledRectangle(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderEnemy(final RectangleHitbox entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderEnemy'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderBasicPlatform(final RectangleHitbox entity) {
        this.fillRectangle(Color.GREEN, this.graphics.get(), this.createScaledRectangle(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderVanishingPlatform(final RectangleHitbox entity) {
        this.fillRectangle(Color.RED, this.graphics.get(), this.createScaledRectangle(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderBrokenPlatform(final RectangleHitbox entity) {
        final Color brown = new Color(139, 69, 19);
        this.fillRectangle(brown, this.graphics.get(), this.createScaledRectangle(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderCoin(final CircleHitbox entity) {
        graphics.get().setColor(Color.YELLOW);
        graphics.get().fillOval((int) (entity.getCenter().getX() * widthRatio), 
        (int) (entity.getCenter().getY() * heightRatio), 
        this.createScaledRadius(entity), this.createScaledRadius(entity));
    }

    /**
     * Setter for the ratio real dimension / world dimension.
     * @param realWidth width of the window of the game.
     * @param realHeight height of the window of the game.
     */
    public void setRatio(final double realWidth, final double realHeight) {
        this.widthRatio = realWidth / this.worldWidth;
        this.heightRatio = realHeight / this.worldHeight;
    }

    /**
     * Setter for the current graphics used to render.
     * @param graphics java swing graphics to render in the game.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
        justification = "graphics is used only by this class and by GamePanel superclasses.")
    public void setGraphics(final Graphics2D graphics) {
        this.graphics = Optional.of(graphics);
    }

    /*The origin in World is the lower left vertex of the window, while in java swing is the upper left
     * vertex, so rectangle's ordinate is computed with height - ordinate. 
    */
    private Rectangle createScaledRectangle(final RectangleHitbox hitbox) {
        return new Rectangle(
            new Point(
                (int) (hitbox.getRectangleLeftX() * this.widthRatio),
                (int) ((this.worldHeight - hitbox.getRectangleUpperY()) * this.heightRatio)
            ),
            new Dimension(
                (int) (hitbox.getWidth() * this.widthRatio),
                (int) (hitbox.getHeight() * this.heightRatio)
            )
        );
    }

    private void fillRectangle(final Color color, final Graphics2D graphics, final Rectangle rectangle) {
        graphics.setColor(color);
        graphics.fill(rectangle);
    }

    private int createScaledRadius(final CircleHitbox hitbox) {
        return (int) (hitbox.getRadius() * this.widthRatio);
    }
}
