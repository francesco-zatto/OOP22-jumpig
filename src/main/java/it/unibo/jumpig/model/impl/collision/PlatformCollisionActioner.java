package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.AbstractCollisionActioner;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.VelocityImpl;

/**
 * Class that changes player's velocity after a jump on a platform.
 * @param <P> any kind of platform.
 */
public class PlatformCollisionActioner<P extends Platform> extends AbstractCollisionActioner<RectangleHitbox, P> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void actOnPlayer(final Player player, final P gameEntity) {
        final var platformJumpVelocity = gameEntity.getJumpVelocity();
        player.setVelocityFromJump(
            new VelocityImpl(platformJumpVelocity.getXComponent(), platformJumpVelocity.getYComponent())
        );
        player.setLastPlatformHeight(gameEntity.getPosition().getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void actOnEntity(final Player player, final P gameEntity) {
    }
}
