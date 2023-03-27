package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.Velocity;

/**
 * An interface that represents the enemy of the game, an entity which
 * the player should avoid, otherwise he will lose a life, or worse, die.
 */
public interface Enemy extends GameEntity<RectangleHitbox>, Targettable {

    /**
     * This method should set a different velocity for each types of the enemies.
     * 
     * @param velocity is the enemies velocity based from the difficult.
     */
    Velocity setVelocity(Velocity velocity);

}
