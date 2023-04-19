package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.CollisionActioner;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that implements the behaviour of the player when collides with an enemy, i.e the player lose 
 * a life and the enemy disappears.
 */
public class EnemyCollisionActioner implements CollisionActioner<RectangleHitbox, Enemy> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void act(final Player player, final Enemy gameEntity) {
        player.decreaseLives();
        gameEntity.markTarget();
    }
}
