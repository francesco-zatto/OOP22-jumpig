package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.model.impl.gameentity.VanishingPlatform;

/**
 * Class that checks the collision of a player with a vanishing platform.
 */
public class VanishingPlatformCollisionChecker extends PlatformCollisionChecker<VanishingPlatform> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean canEntityCollide(final VanishingPlatform gameEntity) {
        return !gameEntity.isTaken();
    }
}
