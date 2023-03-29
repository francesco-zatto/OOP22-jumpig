package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.PlayerHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.Velocity;
import it.unibo.jumpig.model.api.gameentity.AbstractGameEntity;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.VelocityImpl;

/**
 * Class that manages the player in the game.
 */
public class PlayerImpl extends AbstractGameEntity<RectangleHitbox> implements Player {

    private static final int MAXLIVES = 3;
    private Velocity playerVelocity;
    private int lives = MAXLIVES;
    private int coins;

    /**
     * Constructor for the player.
     * @param position position of the player in the world
     */
    public PlayerImpl(final Position position) {
        super(position, new PlayerHitbox(position));
        this.playerVelocity = new VelocityImpl(0, 0);
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
        this.coins++;
    }
}
