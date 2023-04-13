package it.unibo.jumpig.view.api;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.gameentity.GameEntity;

/**
 * Interface that represents the renderer of the App, that has to render every gameEntity of the Game.
 */
public interface Renderer {

    /**
     * Method that renders the player in the View.
     * @param entity player to render.
     */
    void renderPlayer(GameEntity<RectangleHitbox> entity);
}
