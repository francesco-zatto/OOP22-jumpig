package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.Velocity;
import it.unibo.jumpig.model.api.gameentity.AbstractGameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that represents the player in the game.
 */
public class PlayerImpl extends AbstractGameEntity<RectangleHitbox> implements Player {

    private Velocity playerVelocity;
    private int lives;
    /**
     * Constructor for the player.
     * @param position position of the player in the world
     * @param hitbox hitbox of the platform
     * @param playerVelocity the player's initial velocity
     * @param lives the player's total lives
     */
    public PlayerImpl(final Position position, final RectangleHitbox hitbox, 
        final Velocity playerVelocity, final int lives) {
        super(position, hitbox);
        this.playerVelocity = playerVelocity;
        this.lives = lives;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLives() {
        return this.lives;
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
        if (this.lives > 0) {
            this.lives--;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVelocityFromJump(final Velocity velocity) {
        this.playerVelocity = velocity;
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
