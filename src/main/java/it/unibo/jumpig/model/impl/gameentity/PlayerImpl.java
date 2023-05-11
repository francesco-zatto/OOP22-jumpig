package it.unibo.jumpig.model.impl.gameentity;

import java.util.Optional;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.Direction;
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

    private static final double INITIAL_VELOCITY = 20;
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
        this.playerVelocity = new VelocityImpl(0, INITIAL_VELOCITY);
        this.coins = 0;
        this.lastPlatformHeight = Optional.ofNullable(1.0);
    }

    /**
     * Private constructor for the defensive copy.
     * @param position player's position
     * @param velocity player's velocity
     * @param coins player's coins
     * @param lives player's lives
     * @param lastPlatformHeight the last platform's height the player has jumped in
     */
    private PlayerImpl(final Position position, final Velocity velocity, 
        final int coins, final int lives, final Optional<Double> lastPlatformHeight) {
        super(position, new PlayerHitbox(position)); 
        this.playerVelocity = velocity;
        this.coins = coins;
        this.lives = lives;
        this.lastPlatformHeight = lastPlatformHeight;
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
            this.lives--;
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
    public void computeVelocity(final double gravity, final double deltaTime, final Direction direction) {
        this.playerVelocity.computeAcceleratedVelocity(gravity, deltaTime);
        this.playerVelocity.computeHorizontalVelocity(direction);
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
     * {@inheritDoc}
     */
    @Override
    public void computePosition(final double deltaTime) {
        final var finalPosition = this.playerVelocity.computeMovement(super.getPosition(), deltaTime);
        super.setPosition(finalPosition);
        this.getHitbox().updateHitBox(finalPosition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player copy() {
        return new PlayerImpl(this.getPosition(), this.getVelocity(), this.getCoins(), 
        this.getLives(), this.getLastPlatformHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLastPlatformHeight(final Optional<Double> lastPlatformHeight) {
        this.lastPlatformHeight = lastPlatformHeight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Double> getLastPlatformHeight() {
        return this.lastPlatformHeight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveToEdges(final Position corner) {
        super.setPosition(corner);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RectangleHitbox createScaledHitbox(Position position) {
        return new PlayerHitbox(position);
    }
}
