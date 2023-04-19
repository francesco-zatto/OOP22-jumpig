package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.AbstractCollisionChecker;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that checks if a player is jumping on a platform.
 * @param <P> any kind of platform.
 */
public abstract class PlatformCollisionChecker<P extends Platform> extends AbstractCollisionChecker<RectangleHitbox, P> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean areBoundsColliding(final Player player, final P gameEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'areBoundsColliding'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean canPlayerCollide(final Player player) {
        return player.getVelocity().getYComponent() <= 0;
    }
}
