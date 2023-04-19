package it.unibo.jumpig.model.api.collision;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Abstract class of CollisionChecker that implements the way every collision is checked.
 * @param <H> any kind of hitbox
 * @param <E> any kind of game entity
 */
public class AbstractCollisionChecker<H extends Hitbox, E extends GameEntity<H>> implements CollisionChecker<H, E> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(final Player player, final E gameEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'check'");
    }
}
