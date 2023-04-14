package it.unibo.jumpig.view.impl;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.view.api.Renderer;
import it.unibo.jumpig.view.api.RenderingComponent;

/**
 * Class that renders a Basic Platform using a Renderer.
 */
public class BasicPlatformRenderingComponent implements RenderingComponent<RectangleHitbox> {

    /**
     * Method that renders a Basic Platform, that is a gameEntity with a rectangle hitbox
     * using renderer.
     */
    @Override
    public void render(final GameEntity<RectangleHitbox> entity, final Renderer renderer) {
        renderer.renderBasicPlatform(entity);
    }
}
