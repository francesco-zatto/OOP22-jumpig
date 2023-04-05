package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * Class to manage the position and the hitbox of each gameEntity.
 * @param <H> any kind of Hitbox
*/
public abstract class AbstractGameEntity<H extends Hitbox> implements GameEntity<H> {

    private Position position;
    private final H hitbox;

    /**
     * Constructor for any gameEntity.
     * @param position position of the gameEntity in the world.
     * @param hitbox hitbox of the gameEntity.
     */
    public AbstractGameEntity(final Position position, final H hitbox) {
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
    public final H getHitbox() {
        return this.hitbox;
    }

    /**
     * Setter for the position og the gameEntity.
     * @param position new position of the gameEntity
     */
    protected final void setPosition(final Position position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((hitbox == null) ? 0 : hitbox.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractGameEntity<H> other = (AbstractGameEntity<H>) obj;
        if (position == null) {
            if (other.position != null) {
                return false;
            }
        } else if (!position.equals(other.position)) {
            return false;
        }
        if (hitbox == null) {
            if (other.hitbox != null) {
                return false;
            }
        } else if (!hitbox.equals(other.hitbox)) {
            return false;
        }
        return true;
    }
}
