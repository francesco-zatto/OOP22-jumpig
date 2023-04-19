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

    private final CollisionActioner<H, E> actioner;
    private final CollisionChecker<H, E> checker;

    /**
     * Constructor for a collision handler for a player and an entity.
     * @param actioner actioner that modifies player and entity status after a collision
     * @param checker checker that checks if player and entity are colliding.
     */
    public CollisionHandlerImpl(final CollisionActioner<H, E> actioner, final CollisionChecker<H, E> checker) {
        this.actioner = actioner;
        this.checker = checker;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final Player player, final E gameEntity) {
        if (this.checker.check(player, gameEntity)) {
            this.actioner.act(player, gameEntity);
        }
    }
}
