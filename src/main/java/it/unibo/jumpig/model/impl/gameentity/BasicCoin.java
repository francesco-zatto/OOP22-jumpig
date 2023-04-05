package it.unibo.jumpig.model.impl.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.model.api.gameentity.AbstractGameEntity;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * The class that represents a basic coin.
 * It has always the same value and once taken by the player it disappears.
 */
public class BasicCoin extends AbstractGameEntity<CircleHitbox> implements Coin {

    private boolean taken;

    /**
     * The constructor for a basic coin.
     * @param position position of the coin in the world.
     * @param hitbox hitbox of the coin.
     */
    public BasicCoin(final Position position, final CircleHitbox hitbox) {
        super(position, hitbox);
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

    @Override
    public void handleCollision(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleCollision'");
    }
}
