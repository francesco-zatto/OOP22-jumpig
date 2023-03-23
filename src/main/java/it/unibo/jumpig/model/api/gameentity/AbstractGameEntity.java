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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.position == null) ? 0 : this.position.hashCode());
        result = prime * result + ((this.hitbox == null) ? 0 : this.hitbox.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
        final AbstractGameEntity other = (AbstractGameEntity) obj;
        if (this.position == null) {
            if (other.position != null) {
                return false;
            }
        } else if (!position.equals(other.position)) {
            return false;
        }
        if (this.hitbox == null) {
            if (other.hitbox != null) {
                return false;
            }
        } else if (!this.hitbox.equals(other.hitbox)) {
            return false;
        }
        return true;
    }
}
