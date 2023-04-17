package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;

/**
 * An interface that represents the enemy of the game, an entity which
 * the player should avoid, otherwise he will lose a life, or worse, die.
 */
public interface Enemy extends GameEntity<RectangleHitbox>, Targettable, Collidable {

}
