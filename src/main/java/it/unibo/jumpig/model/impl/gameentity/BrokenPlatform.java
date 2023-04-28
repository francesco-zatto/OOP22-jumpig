package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.PlatformHitbox;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.api.gameentity.TargettablePlatform;

/**
 * Class that represents a broken platform, which, when the player jumps on it, it does not give any velocity to the
 * player. Instead it will be entirely broken and will be not available.
 */
public class BrokenPlatform extends TargettablePlatform {

    /**
     * Constructor for a broken platform.
     * @param position position of the platform in the game's world
     * @param hitbox hitbox of the platform
     */
    protected BrokenPlatform(final Position position, final PlatformHitbox hitbox) {
        super(position, hitbox, 0);
        //TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCollision(final Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleCollision'");
    } 
}
