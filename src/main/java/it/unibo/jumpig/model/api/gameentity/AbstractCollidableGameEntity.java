package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.collision.CollisionHandler;

/**
 * Class to manage the way every collidable entity collides with the player.
 * @param <H> any kind of Hitbox
 * @param <E> any kind of gameEntity
 */
public abstract class AbstractCollidableGameEntity<H extends Hitbox, E extends GameEntity<H>> extends AbstractGameEntity<H>
        implements Collidable {

    private final CollisionHandler<H, E> collisionHandler;

    /**
     * Constructor for any collidable gameEntity.
     * @param position position of the gameEntity in the world.
     * @param hitbox hitbox of the gameEntity.
     * @param collisionHandler subtype of {@link it.unibo.jumpig.model.api.collision.CollisionHandler}
     */
    protected AbstractCollidableGameEntity(final Position position, final H hitbox,
            final CollisionHandler<H, E> collisionHandler) {
        super(position, hitbox);
        this.collisionHandler = collisionHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void handleCollision(Player player);

    /**
     * Getter for the collisionHandler of the entity of type E.
     * @return the collision handler.
     */
    protected final CollisionHandler<H, E> getCollisionHandler() {
        return this.collisionHandler;
    }
}
