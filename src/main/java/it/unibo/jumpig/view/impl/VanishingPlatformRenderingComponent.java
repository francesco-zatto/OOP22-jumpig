package it.unibo.jumpig.view.impl;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.view.api.Renderer;
import it.unibo.jumpig.view.api.RenderingComponent;

/**
 * Class that renders a Vanishing Platform using a Renderer.
 * {@link it.unibo.jumpig.model.impl.gameentity.VanishingPlatform}
 */
public class VanishingPlatformRenderingComponent implements RenderingComponent<RectangleHitbox> {

    /**
     * Method that renders a Vanishing Platform, that is a gameEntity with a RectangleHitbox, 
     * using renderer.
     */
    @Override
    public void render(final GameEntity<RectangleHitbox> entity, final Renderer renderer) {
        renderer.renderVanishingPlatform(entity);
    }
}
