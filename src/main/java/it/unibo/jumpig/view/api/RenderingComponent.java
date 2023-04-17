package it.unibo.jumpig.view.api;

import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * Interface that renders a hitbox using a renderer.
 * {@link it.unibo.jumpig.view.api.Renderer}
 */
@FunctionalInterface
public interface RenderingComponent {

    /**
     * Method that renders the entity in the argument using a renderer.
     * @param entity entity to render
     * @param renderer renderer used to render the entity.
     */
    void render(Hitbox entity, Renderer renderer);
}
