package it.unibo.jumpig.view.api;

import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;

/**
 * Interface that represents the renderer of the App, that has to render every gameEntity of the Game.
 */
public interface Renderer {

    /**
     * Method that renders the player in the View.
     * @param entity player to render.
     */
    void renderPlayer(RectangleHitbox entity);

    /**
     * Method that renders an enemy in the View.
     * @param entity enemy to render.
     */
    void renderEnemy(RectangleHitbox entity);

    /**
     * Method that renders a basic platform in the View.
     * @param entity basic platform to render.
     */
    void renderBasicPlatform(RectangleHitbox entity);

    /**
     * Method that renders a vanishing platform in the View.
     * @param entity vanishing platform to render.
     */
    void renderVanishingPlatform(RectangleHitbox entity);

    /**
     * Method that renders a broken platform in the View.
     * @param entity broken platform to render.
     */
    void renderBrokenPlatform(RectangleHitbox entity);

    /**
     * Method that renders a coin in the View.
     * @param entity coin to render.
     */
    void renderCoin(CircleHitbox entity);
}
