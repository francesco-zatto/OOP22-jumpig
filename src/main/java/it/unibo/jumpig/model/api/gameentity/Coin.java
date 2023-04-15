package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;

/**
 * An interface that represents the coin which is collectible by the player.
 */
public interface Coin extends GameEntity<CircleHitbox>, Targettable, Collidable {

}
