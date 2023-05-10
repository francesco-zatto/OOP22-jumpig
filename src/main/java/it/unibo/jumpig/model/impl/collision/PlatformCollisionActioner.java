package it.unibo.jumpig.model.impl.collision;

import java.util.Optional;

import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.collision.CollisionActioner;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.VelocityImpl;

/**
 * Class that changes player's velocity after a jump on a platform.
 * @param <P> any kind of platform.
 */
public class PlatformCollisionActioner<P extends Platform> implements CollisionActioner<RectangleHitbox, P> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void act(final Player player, final P gameEntity) {
        final var platformJumpVelocity = gameEntity.getJumpVelocity();
        player.setVelocityFromJump(new VelocityImpl(
            player.getVelocity().getXComponent(),
            platformJumpVelocity.getYComponent()
        ));
        player.setLastPlatformHeight(Optional.of(gameEntity.getPosition().getY()));
    }
}
