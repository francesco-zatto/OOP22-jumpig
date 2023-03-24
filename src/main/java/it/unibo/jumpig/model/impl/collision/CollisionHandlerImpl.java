package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.common.api.hitbox.ShapeHitbox;
import it.unibo.jumpig.model.api.collision.CollisionActioner;
import it.unibo.jumpig.model.api.collision.CollisionChecker;
import it.unibo.jumpig.model.api.collision.CollisionHandler;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that handles possible collision between a player and a gameEntity.
 * @param <S> any kind of ShapeHitbox
 * @param <H> any kind of Hitbox
 * @param <E> any kind of gameEntity that the player could collide with
 */
public class CollisionHandlerImpl<S extends ShapeHitbox, H extends Hitbox<S>, E extends GameEntity<S, H>> 
        implements CollisionHandler<S, H, E> {

    private final CollisionChecker<S, H, E> collisionChecker;
    private final CollisionActioner<S, H, E> collisionActioner;

    /**
     * Constructor that creates a new CollisionHandler, which depends on the collisionChecker and
     * on the collisionActioner.
     * @param collisionChecker
     * @param collisionActioner
     */
    public CollisionHandlerImpl(final CollisionChecker<S, H, E> collisionChecker, 
            final CollisionActioner<S, H, E> collisionActioner) {
        this.collisionChecker = collisionChecker;
        this.collisionActioner = collisionActioner;
    }

    /**
     * Method that, given a collisionChecker and a collisionActioner, checks a possible 
     * collision and then it acts on the player and the other gameEntity that have collided.
    */
    @Override
    public void handle(final Player player, final E gameEntity) {
        if (this.collisionChecker.check(player, gameEntity)) {
            collisionActioner.act(player, gameEntity);
        }
    }
}
