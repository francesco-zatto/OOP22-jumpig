package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.model.api.gameentity.TargettablePlatform;

/**
 * Class that checks the collision of a player with any kind of targettable platform.
 * @param <T> any kind of platform that extends TargettablePlatform 
 */
public class TargettablePlatformCollisionChecker<T extends TargettablePlatform> extends PlatformCollisionChecker<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean canEntityCollide(final T gameEntity) {
        return !gameEntity.isTaken();
    }
}
