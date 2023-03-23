package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * Class to manage the position and the hitbox of each gameEntity.
*/
public abstract class AbstractGameEntity implements GameEntity {

    private final Position position;
    private final Hitbox hitbox;

    /**
     * Constructor for any gameEntity.
     * @param position position of the gameEntity in the world.
     * @param hitbox hitbox of the gameEntity.
     */
    public AbstractGameEntity(final Position position, final Hitbox hitbox) {
        this.position = position;
        this.hitbox = hitbox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Position getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Hitbox getHitbox() {
        return this.hitbox;
    }
}
