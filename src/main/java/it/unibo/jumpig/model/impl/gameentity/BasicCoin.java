package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.common.impl.hitbox.CoinHitbox;
import it.unibo.jumpig.model.api.gameentity.AbstractGameEntity;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.collision.CoinCollisionHandler;

/**
 * The class that represents a basic coin.
 * It has always the same value and once taken by the player it disappears.
 */
public class BasicCoin extends AbstractGameEntity<CircleHitbox> implements Coin {

    private boolean taken;
    private final CoinCollisionHandler collisionHandler = new CoinCollisionHandler();

    /**
     * The constructor for a basic coin.
     * @param position position of the coin in the world.
     */
    public BasicCoin(final Position position) {
        super(position, new CoinHitbox(position), (e, r) -> r.renderCoin(e.getHitbox()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void markTarget() {
        this.taken = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTaken() {
        return this.taken;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCollision(final Player player) {
        this.collisionHandler.handle(player, this);
    }
}
