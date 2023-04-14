package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.model.api.gameentity.AbstractPlatform;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.collision.BasicPlatformCollisionHandler;

/**
 * Class that represents a basic platform, that simply causes the player's jump.
 */
public class BasicPlatform extends AbstractPlatform {

    private final BasicPlatformCollisionHandler collisionHandler = new BasicPlatformCollisionHandler();
    /**
     * Constructor for a basic platform that simply causes the jump of the player.
     * @param position position of the platform.
     * @param verticalJumpVelocity vertical velocity of a player when jumps on the platform.
     */
    public BasicPlatform(final Position position, final double verticalJumpVelocity) {
        super(position, verticalJumpVelocity, (e, r) -> r.renderBasicPlatform(e));
    }

    @Override
    public final void handleCollision(final Player player) {
        this.collisionHandler.handle(player, this);
    }
}
