package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.gameentity.BrokenPlatform;

/**
 * Class that does not chenge player's velocity after a jump, instead the player breaks the 
 * broken platform which he tries to jump on.
 */
public class BrokenPlatformCollisionActioner extends PlatformCollisionActioner<BrokenPlatform> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void act(final Player player, final BrokenPlatform gameEntity) {
        gameEntity.markTarget();
    }
}
