package it.unibo.jumpig.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.view.api.Renderer;

/**
 * Class the uses Java Swing library to render the gameEntities.
 * To render them it uses a Graphics2D object, that must be setted every time rendering is needed.
 * The Graphics2D object must not be disposed or deleted in other ways before the actual rendering of game entities.
 * {@link java.awt.Graphics2D}
 */
public final class SwingRenderer implements Renderer {

    private Optional<Graphics2D> graphics = Optional.empty();
    private final ImageLoader imageLoader = new ImageLoader();
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
        final var rectangle = this.createScaledRectangle(entity);
        drawImage(rectangle, this.imageLoader.getJumpigImage());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderEnemy(final RectangleHitbox entity) {
        final var rectangle = this.createScaledRectangle(entity);
        drawImage(rectangle, this.imageLoader.getEnemyImage());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderBasicPlatform(final RectangleHitbox entity) {
        final var rectangle = this.createScaledRectangle(entity);
        drawImage(rectangle, this.imageLoader.getBasicPlatformImage());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderVanishingPlatform(final RectangleHitbox entity) {
        final var rectangle = this.createScaledRectangle(entity);
        drawImage(rectangle, this.imageLoader.getVanishingPlatformImage());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderBrokenPlatform(final RectangleHitbox entity) {
        final var rectangle = this.createScaledRectangle(entity);
        drawImage(rectangle, this.imageLoader.getBrokenPlatformImage());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderCoin(final CircleHitbox entity) {
        graphics.get().setColor(Color.YELLOW);
        graphics.get().fillOval(
            this.createScaledLeftX(entity), 
            this.createScaledUpperY(entity), 
            this.createWidthScaledRadius(entity) * 2, 
            this.createHeightScaledRadius(entity) * 2
            );
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

    private void drawImage(final Rectangle rectangle, final Image image) {
        this.graphics.get().drawImage(image, 
            (int) rectangle.getX(), 
            (int) rectangle.getY(), 
            (int) rectangle.getWidth(), 
            (int) rectangle.getHeight(), 
            null
        );
    }

    /*The origin in World is the lower left vertex of the window, while in java swing is the upper left
     * vertex, so rectangle's ordinate is computed with height - ordinate. 
    */
    private Rectangle createScaledRectangle(final RectangleHitbox hitbox) {
        return new Rectangle(
            new Point(
                (int) (hitbox.getLeftX() * this.widthRatio),
                (int) ((this.worldHeight - hitbox.getUpperY()) * this.heightRatio)
            ),
            new Dimension(
                (int) (hitbox.getWidth() * this.widthRatio),
                (int) (hitbox.getHeight() * this.heightRatio)
            )
        );
    }

    /**
     * Method to scale the width of the coin.
     * @param hitbox the coin hitbox
     * @return the scaled width radius of the coin
     */
    private int createWidthScaledRadius(final CircleHitbox hitbox) {
        return (int) (hitbox.getRadius() * this.widthRatio);
    }

    /**
     * Method to scale the height of the coin.
     * @param hitbox the coin hitbox
     * @return the scaled height radius of the coin
     */
    private int createHeightScaledRadius(final CircleHitbox hitbox) {
        return (int) (hitbox.getRadius() * this.heightRatio);
    }

    /**
     * The method to create the scaled upper left corner of the oval to be filled.
     * @param entity the oval to be filled
     * @return the scaled left x
     */
    private int createScaledLeftX(final CircleHitbox entity) {
        return (int) (entity.getLeftX() * widthRatio);
    }

    /**
     * The method to create the scaled upper left corner of the oval to be filled.
     * @param entity the oval to be filled
     * @return the scaled upper y
     */
    private int createScaledUpperY(final CircleHitbox entity) {
        return (int) ((this.worldHeight - entity.getUpperY()) * heightRatio);
    }
}
