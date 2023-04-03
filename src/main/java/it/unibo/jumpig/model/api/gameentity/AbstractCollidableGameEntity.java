package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.collision.CollisionHandler;

/**
 * Class to manage the way every collidable entity collides with the player.
 * @param <H> any kind of Hitbox
 */
public abstract class AbstractCollidableGameEntity<H extends Hitbox> extends AbstractGameEntity<H> implements Collidable {

    private final CollisionHandler<H, GameEntity<H>> collisionHandler;

    /**
     * Constructor for any collidable gameEntity.
     * @param position position of the gameEntity in the world.
     * @param hitbox hitbox of the gameEntity.
     * @param collisionHandler subtype of {@link it.unibo.jumpig.model.api.collision.CollisionHandler}
     */
    public AbstractCollidableGameEntity(final Position position, final H hitbox,
            final CollisionHandler<H, GameEntity<H>> collisionHandler) {
        super(position, hitbox);
        this.collisionHandler = collisionHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void handleCollision(final Player player) {
        this.collisionHandler.handle(player, this);
    }
}
