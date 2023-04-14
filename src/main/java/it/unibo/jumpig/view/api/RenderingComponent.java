package it.unibo.jumpig.view.api;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.gameentity.GameEntity;

/**
 * Interface that renders a gameEntity using a renderer.
 * {@link it.unibo.jumpig.model.api.gameentity.GameEntity}
 * {@link it.unibo.jumpig.view.api.Renderer}
 * @param <H> any kind of hitbox
 */
public interface RenderingComponent<H extends Hitbox> {

    /**
     * Method that renders the entity in the argument using a renderer.
     * @param entity entity to render
     * @param renderer renderer used to render the entity.
     */
    void render(GameEntity<H> entity, Renderer renderer);
}
