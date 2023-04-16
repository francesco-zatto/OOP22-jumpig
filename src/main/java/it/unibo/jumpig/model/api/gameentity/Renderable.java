package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.view.api.Renderer;

/**
 * Any class that implements this interface is renderable in the View.
 */
public interface Renderable {
    /**
     * Method that renders using the renderer in the argument.
     * @param renderer renderer used to render
     */
    void updateRendering(Renderer renderer);
}
