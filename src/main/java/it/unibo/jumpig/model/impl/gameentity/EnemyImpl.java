package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.EnemyHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.CollisionHandler;
import it.unibo.jumpig.model.api.gameentity.AbstractGameEntity;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.collision.CollisionHandlerImpl;
import it.unibo.jumpig.model.impl.collision.EnemyCollisionActioner;
import it.unibo.jumpig.model.impl.collision.EnemyCollisionChecker;

/**
 * This class EnemyImpl should represents an enemy.
 * It could be different types and once it touch the player,
 * it disappears and the player lost a life.
 */
public class EnemyImpl extends AbstractGameEntity<RectangleHitbox> implements Enemy {

    private boolean targettable;
    private final CollisionHandler<RectangleHitbox, Enemy> collisionHandler = new CollisionHandlerImpl<>(
        new EnemyCollisionActioner(), 
        new EnemyCollisionChecker()
    );

    /**
     * The constructor for an enemy.
     * 
     * @param position position of the enemy in the world
     */

    public EnemyImpl(final Position position) {
        super(position, new EnemyHitbox(position));
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

    /**
     * {@inheritDoc}
     */
    @Override
    public RectangleHitbox createScaledHitbox(final Position position) {
        return new EnemyHitbox(position);
    }

}
