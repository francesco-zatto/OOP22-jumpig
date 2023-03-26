package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.Rectangle;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.Velocity;
import it.unibo.jumpig.model.api.gameentity.AbstractGameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that represents the player in the game.
 */
public class PlayerImpl extends AbstractGameEntity<Rectangle, RectangleHitbox> implements Player {

    private final Velocity playerVelocity;
    /**
     * Constructor for the player.
     * @param position position of the player in the world
     * @param hitbox hitbox of the platform
     * @param playerVelocity the player's initial velocity
     */
    public PlayerImpl(final Position position, final RectangleHitbox hitbox, final Velocity playerVelocity) {
        super(position, hitbox);
        this.playerVelocity = playerVelocity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLives() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLives'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Velocity getVelocity() {
        return this.playerVelocity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseLives() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'decreaseLives'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVelocityFromJump(final Velocity velocity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVelocityFromJump'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void computeVelocity(final double gravity, final double deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'computeVelocity'");
    }
}
