package it.unibo.jumpig.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.view.api.Renderer;

/**
 * Class the uses Java Swing library to render the gameEntity.
 */
public final class SwingRenderer implements Renderer {

    private final Graphics2D graphics;
    private final double worldWidth;
    private final double worldHeight;
    private double widthRatio;
    private double heightRatio;

    /**
     * Constructor for a SwingRenderer.
     * @param graphics Java Swing graphics used to render game entities.
     * @param worldWidth width of the game's world.
     * @param worldHeight height of the game's world.
     * @param realWidth width of the game's panel.
     * @param realHeight heigth of the game's panel.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
            justification = "graphics is used only by this class and by GamePanel superclasses that"
                    + "should not make no change in the way game entities are rendered.")
    public SwingRenderer(final Graphics2D graphics, final double worldWidth, final double worldHeight,
            final double realWidth, final double realHeight) {
        this.graphics = graphics;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.widthRatio = realWidth / this.worldWidth;
        this.heightRatio = realHeight / this.worldHeight; 
    } 

    @Override
    public void renderPlayer(final RectangleHitbox entity) {
        graphics.setColor(Color.PINK);
        graphics.fill(this.createScaledRectangle(entity));
    }

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
        graphics.setColor(Color.GREEN);
        graphics.fill(this.createScaledRectangle(entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderVanishingPlatform(final RectangleHitbox entity) {
        graphics.setColor(Color.RED);
        graphics.fill(this.createScaledRectangle(entity));
    }

    @Override
    public void renderCoin(final CircleHitbox entity) {
        graphics.setColor(Color.YELLOW);
        graphics.fillOval((int) (entity.getCenter().getX() * widthRatio), 
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

    private Rectangle createScaledRectangle(final RectangleHitbox hitbox) {
        return new Rectangle(new Point((int) (hitbox.getCenter().getX() * this.widthRatio),
                (int) (hitbox.getCenter().getY() * this.heightRatio)),
                new Dimension((int) (hitbox.getWidth() * this.widthRatio),
                        (int) (hitbox.getHeight() * this.heightRatio)));
    }

    private int createScaledRadius(final CircleHitbox hitbox) {
        return (int) (hitbox.getRadius() * this.widthRatio);
    }

    /*public static void main(String[] args) {
        var hitbox = new RectangleHitbox(new PositionImpl(10, 10), 20, 5);
        var r = new SwingRenderer(null, 200, 100, 50, 75);
        System.out.println(r.scale(hitbox));
    }*/
}
