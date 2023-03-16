package it.unibo.jumpig.model.api;

import it.unibo.jumpig.common.api.Position;

/*
 * Interface that represent an entity in the game.
 */
public interface GameEntity {
    
    /**
     * Getter for the position of the GameEntity.
     * @return the GameEntity's position.
     */
    Position getPosition();

}
