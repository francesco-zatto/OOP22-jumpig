package it.unibo.jumpig.view.impl;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Class that loads the images needed for the SwingRenderer. When an ImageLoader instance is created, it has the references
 * to all the images for each game entity, calling the corresponding method.
 */
@SuppressFBWarnings(
    value = "EI_EXPOSE_REP",
    justification = "This class is meant to collect all the references to the images needed in the game "
            + "otherwise the images should be loaded for every frame and it would slow down considerably the game."
)
public class ImageLoader {
    private static final String ROOT = "it/unibo/jumpig/images/";
    private static final String BASIC_PLATFORM_FILE = ROOT + "basic_platform.png";
    private static final String VANISHING_PLATFORM_FILE = ROOT + "vanishing_platform.png";
    private static final String BROKEN_PLATFORM_FILE = ROOT + "broken_platform.png";
    private static final String ENEMY_FILE = ROOT + "jumpier_enemy.png";
    private static final String PLAYER_FILE = ROOT + "jumpig_player.png";
    private final Image basicPlatformImage;
    private final Image vanishingPlatformImage; 
    private final Image brokenPlatformImage;
    private final Image enemyImage;
    private final Image jumpigImage;

    /**
     * Constructor for an ImageLoader.
     */
    public ImageLoader() {
        final URL basicUrl = ClassLoader.getSystemResource(BASIC_PLATFORM_FILE);
        this.basicPlatformImage = new ImageIcon(basicUrl).getImage();
        final URL vanishingUrl = ClassLoader.getSystemResource(VANISHING_PLATFORM_FILE);
        this.vanishingPlatformImage = new ImageIcon(vanishingUrl).getImage();
        final URL brokenUrl = ClassLoader.getSystemResource(BROKEN_PLATFORM_FILE);
        this.brokenPlatformImage = new ImageIcon(brokenUrl).getImage();
        final URL enemyUrl = ClassLoader.getSystemResource(ENEMY_FILE);
        this.enemyImage = new ImageIcon(enemyUrl).getImage();
        final URL jumpigUrl = ClassLoader.getSystemResource(PLAYER_FILE);
        this.jumpigImage = new ImageIcon(jumpigUrl).getImage();
    }

    /**
     * Getter for the basic platform's image.
     * @return basic platform's image.
     * {@link it.unibo.jumpig.model.impl.gameentity.BasicPlatform}
     */
    public Image getBasicPlatformImage() {
        return this.basicPlatformImage;
    }

    /**
     * Getter for the vanishing platform's image.
     * @return vanishing platform's image.
     * {@link it.unibo.jumpig.model.impl.gameentity.VanishingPlatform}
     */
    public Image getVanishingPlatformImage() {
        return this.vanishingPlatformImage;
    }

    /**
     * Getter for the broken platform's image.
     * @return broken platform's image.
     * {@link it.unibo.jumpig.model.impl.gameentity.BrokenPlatform}
     */
    public Image getBrokenPlatformImage() {
        return this.brokenPlatformImage;
    }

    /**
     * Getter for the enemy's image.
     * @return enemy's image.
     * {@link it.unibo.jumpig.model.api.gameentity.Enemy}
     */
    public Image getEnemyImage() {
        return this.enemyImage;
    }

    /**
     * Getter for the player's image.
     * @return player's image.
     * {@link it.unibo.jumpig.model.api.gameentity.Player}
     */
    public Image getJumpigImage() {
        return this.jumpigImage;
    }
}
