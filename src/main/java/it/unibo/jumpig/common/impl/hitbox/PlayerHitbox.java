package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.view.api.Renderer;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRendering(final Renderer renderer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRendering'");
    } 
}
