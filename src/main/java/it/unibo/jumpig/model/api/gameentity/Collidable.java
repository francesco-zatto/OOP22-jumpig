package it.unibo.jumpig.model.api.gameentity;

/**
 * The interface to understand how a collidable entity collides with the player.
 */
public interface Collidable {

    /**
     * Method to handle a possible collision between a player and a collidable
     * entity.
     * 
     * @param player player which the collidable entity has collided with.
     */
    void handleCollision(Player player);

}
