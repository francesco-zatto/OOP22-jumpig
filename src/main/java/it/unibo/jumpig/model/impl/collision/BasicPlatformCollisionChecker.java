package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.model.impl.gameentity.BasicPlatform;

/**
 * Class that checks the collision of the player with a basic platform.
 */
public class BasicPlatformCollisionChecker extends PlatformCollisionChecker<BasicPlatform> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean canEntityCollide(final BasicPlatform gameEntity) {
        return true;
    }
}
