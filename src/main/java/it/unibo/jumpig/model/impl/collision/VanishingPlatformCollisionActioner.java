package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.gameentity.VanishingPlatform;

/**
 * Class that changes the visibility of a vanishing platform after a jump.
 */
public class VanishingPlatformCollisionActioner extends PlatformCollisionActioner<VanishingPlatform> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void act(final Player player, final VanishingPlatform gameEntity) {
        super.act(player, gameEntity);
        gameEntity.markTarget();
    }
}
