package it.unibo.jumpig.model.impl.collision;

import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.model.api.collision.CollisionActioner;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Player;

/**
 * Class that implements the behaviour of player and a coin when they collide, i.e. the player picks
 * the coin and the coin disappears.
 */
public class CoinCollisionActioner implements CollisionActioner<CircleHitbox, Coin> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void act(final Player player, final Coin gameEntity) {
        player.incrementCoins();
        gameEntity.markTarget();
    }
}
