package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * Interface that should be implemented by classes that are GameEntity and Collidable.
 * @param <H> any kind of hitbox
 */
public interface CollidableEntity<H extends Hitbox> extends GameEntity<H>, Collidable {

}
