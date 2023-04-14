package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.view.api.RenderingComponent;

/**
 * Interface that represent an entity in the game.
 * @param <H> any kind of Hitbox
 */
public interface GameEntity<H extends Hitbox> {
    /**
     * Getter for the position of the gameEntity.
     * @return the gameEntity's position.
     */
    Position getPosition();

    /**
     * Getter for the hitbox of the gameEntity.
     * @return the gameEntity's hitbox.
     */
    H getHitbox();

    /**
     * Getter for the rendering component of the gameEntity.
     * @return the gameEntity's rendering component
     */
    RenderingComponent<H> getRenderingComponent();

}
