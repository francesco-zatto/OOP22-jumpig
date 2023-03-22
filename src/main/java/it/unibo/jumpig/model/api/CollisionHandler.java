package it.unibo.jumpig.model.api;

import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Interface whose implementations have to handle collisions between
 * player and a gameEntity.
 */
public interface CollisionHandler<E extends GameEntity> {

    void handle(Player player, E gameEntity);
    
}
