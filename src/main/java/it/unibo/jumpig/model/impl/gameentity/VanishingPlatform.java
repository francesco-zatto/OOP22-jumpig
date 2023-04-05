package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.api.gameentity.Targettable;
import it.unibo.jumpig.model.impl.collision.VanishingPlatformCollisionHandler;

/**
 * Class that represents a platform which, when the player jumps on it, it vanishes.
 */
public class VanishingPlatform extends BasicPlatform implements Targettable {

    private boolean targettable;
    private final VanishingPlatformCollisionHandler collisionHandler;
    /**
     * Constructor for a vanishing platform.
     * @param position position of the platform in the world.
     * @param verticalJumpVelocity vertical velocity of a player when jumps on the platform. 
    */
    public VanishingPlatform(final Position position, final double verticalJumpVelocity) {
        super(position, verticalJumpVelocity);
        this.collisionHandler = new VanishingPlatformCollisionHandler();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void markTarget() {
        this.targettable = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTaken() {
        return this.targettable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCollision(final Player player) {
        this.collisionHandler.handle(player, this);
    }
}
