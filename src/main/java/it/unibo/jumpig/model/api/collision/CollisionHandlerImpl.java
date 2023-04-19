package it.unibo.jumpig.model.api.collision;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class for any kind of collision handler that handles collisions using a collision checker and a collision handler.
 * @param <H> any kind of hitbox
 * @param <E> any kind of game entity
 */
public class CollisionHandlerImpl<H extends Hitbox, E extends GameEntity<H>>  implements CollisionHandler<H, E> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final Player player, final E gameEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }
}
