package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.Velocity;
import it.unibo.jumpig.model.api.gameentity.AbstractGameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that manages the player in the game.
 */
public class PlayerImpl extends AbstractGameEntity<RectangleHitbox> implements Player {

    private static final int MAXLIVES = 3;
    private Velocity playerVelocity;
    private int lives = MAXLIVES;
    private final int coins;

    /**
     * Constructor for the player.
     * @param position position of the player in the world
     * @param hitbox hitbox of the platform
     * @param playerVelocity the player's initial velocity
     */
    public PlayerImpl(final Position position, final RectangleHitbox hitbox, 
        final Velocity playerVelocity) {
        super(position, hitbox);
        this.playerVelocity = playerVelocity;
        this.coins = 0;
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
        this.playerVelocity.computeAcceleratedVelocity(gravity, deltaTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCoins() {
        return this.coins;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementCoins() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'incrementCoins'");
    }
}
