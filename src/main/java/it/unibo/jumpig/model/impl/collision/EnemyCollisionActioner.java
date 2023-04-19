package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.AbstractCollisionActioner;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that implements the behaviour of the player when collides with an enemy, i.e the player lose 
 * a life and the enemy disappears.
 */
public class EnemyCollisionActioner extends AbstractCollisionActioner<RectangleHitbox, Enemy> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void actOnPlayer(final Player player, final Enemy gameEntity) {
        player.decreaseLives();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void actOnEntity(final Player player, final Enemy gameEntity) {
        gameEntity.markTarget();
    }
}
