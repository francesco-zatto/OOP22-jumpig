package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.Velocity;
import it.unibo.jumpig.model.api.gameentity.AbstractGameEntity;
import it.unibo.jumpig.model.api.gameentity.Platform;

/**
 * Class that represents a basic platform, that simply causes the player's jump.
 */
public class BasicPlatform extends AbstractGameEntity implements Platform {

    private final Velocity jumpVelocity;
    private final int length;

    /**
     * Constructor for a basic platform.
     * @param position position of the platform in the world.
     * @param hitbox hitbox of the platform.
     * @param jumpVelocity velocity of a player when jumps on the platform. 
     * @param length horizontal length of the platform
     */
    public BasicPlatform(final Position position, final Hitbox hitbox, final Velocity jumpVelocity,
            final int length) {
        super(position, hitbox);
        this.jumpVelocity = jumpVelocity;
        this.length = length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Velocity getJumpVelocity() {
        return this.jumpVelocity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLength() {
        return this.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((jumpVelocity == null) ? 0 : jumpVelocity.hashCode());
        result = prime * result + length;
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
        final BasicPlatform other = (BasicPlatform) obj;
        if (this.jumpVelocity == null) {
            if (other.jumpVelocity != null) {
                return false;
            }
        } else if (!this.jumpVelocity.equals(other.jumpVelocity)) {
            return false;
        }
        return this.length == other.length;
    }
}
