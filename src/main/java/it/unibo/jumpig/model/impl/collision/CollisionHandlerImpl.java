package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.model.api.collision.CollisionHandler;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that handles possible collision between a player and a gameEntity.
 * @param <E> any kind of gameEntity that the player could collide with.
 */
public class CollisionHandlerImpl<E extends GameEntity> implements CollisionHandler<E> {

    /**
     * Method that, given a collisionChecker and a collisionActioner, checks a possible 
     * collision and then it acts on the player and the other gameEntity that have collided.
    */
    @Override
    public void handle(final Player player, final E gameEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }
}
