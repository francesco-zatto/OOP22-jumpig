package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.gameentity.AbstractGameEntity;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.collision.EnemyCollisionHandler;

/**
 * This class EnemyImpl should represents an enemy.
 * It could be different types and once it touch the player,
 * it disappears and the player lost a life.
 */
public class EnemyImpl extends AbstractGameEntity<RectangleHitbox> implements Enemy {

    private boolean targettable;
    private final EnemyCollisionHandler collisionHandler;

    /**
     * The constructor for an enemy.
     * 
     * @param position position of the enemy in the world
     * @param hitbox   hitbox of the enemy
     */

    public EnemyImpl(final Position position, final RectangleHitbox hitbox) {
        super(position, hitbox);
        this.collisionHandler = new EnemyCollisionHandler();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void markTarget() {
        this.targettable = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTaken() {
        return this.targettable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCollision(final Player player) {
        this.collisionHandler.handle(player, this);
    }

}
