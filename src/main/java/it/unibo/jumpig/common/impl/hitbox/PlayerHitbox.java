package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;

/**
 * Class that represents the player's hitbox.
 */
public class PlayerHitbox extends RectangleHitbox {

    private static final double PLAYER_WIDTH = 1; 
    private static final double PLAYER_HEIGHT = 2;
    /**
     * Constructor for the player's hitbox.
     * @param center center of the hitbox
     */
    public PlayerHitbox(final Position center) {
        super(center, PLAYER_WIDTH, PLAYER_HEIGHT);
    } 
}