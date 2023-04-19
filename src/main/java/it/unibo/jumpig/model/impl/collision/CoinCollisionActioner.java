package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.model.api.collision.AbstractCollisionActioner;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that implements the behaviour of player and a coin when they collide, i.e. the player picks
 * the coin and the coin disappears.
 */
public class CoinCollisionActioner extends AbstractCollisionActioner<CircleHitbox, Coin> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void actOnPlayer(final Player player, final Coin gameEntity) {
        player.incrementCoins();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void actOnEntity(final Player player, final Coin gameEntity) {
        gameEntity.markTarget();
    }
}
