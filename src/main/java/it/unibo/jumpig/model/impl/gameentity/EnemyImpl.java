package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.gameentity.AbstractGameEntity;
import it.unibo.jumpig.model.api.gameentity.Enemy;

/**
 * This class EnemyImpl should represents an enemy.
 * It could be different types and once it touch the player,
 * it disappears and the player lost a life.
 */
public class EnemyImpl extends AbstractGameEntity<RectangleHitbox> implements Enemy {

    private boolean targettable;

    /**
     * The constructor for an enemy.
     * 
     * @param position position of the enemy in the world
     * @param hitbox   hitbox of the enemy
     */

    public EnemyImpl(final Position position, final RectangleHitbox hitbox) {
        super(position, hitbox);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTarget(final boolean setTargettable) {
        this.targettable = setTargettable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTaken() {
        return this.targettable;
    }

}
