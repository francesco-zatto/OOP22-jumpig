package it.unibo.jumpig.model.impl.gameentity;

import java.util.Objects;
import java.util.Optional;

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
    private Optional<Double> lastPlatformHeight;

    /**
     * Constructor for the player.
     * @param position position of the player in the world
     */
    public PlayerImpl(final Position position) {
        super(position, new PlayerHitbox(position));
        this.playerVelocity = new VelocityImpl(0, 0);
        this.coins = 0;
        this.lastPlatformHeight = Optional.empty();
    }

    /**
     * Private constructor for the defensive copy.
     * @param position player's position
     * @param velocity player's velocity
     * @param coins player's coins
     * @param lives player's lives
     */
    private PlayerImpl(final Position position, final Velocity velocity, final int coins, final int lives) {
        super(position, new PlayerHitbox(position));
        this.playerVelocity = velocity;
        this.coins = coins;
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
        this.playerVelocity.computeAcceleratedVelocity(gravity, deltaTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCoins() {
        return this.coins;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementCoins() {
        this.coins++;
    }

    /**
     * Method that computes the next player's position. 
     * @param deltaTime the time interval
     */
    public void computePosition(final double deltaTime) {
        super.setPosition(this.playerVelocity.computeMovement(super.getPosition(), deltaTime));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player copy() {
        return new PlayerImpl(this.getPosition(), this.getVelocity(), this.getCoins(), this.getLives());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLastPlatformHeight(final Double lastPlatformHeight) {
        this.lastPlatformHeight = Optional.of(Objects.requireNonNull(lastPlatformHeight));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLastPlatformHeight() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLastPlatformHeight'");
    }
}
