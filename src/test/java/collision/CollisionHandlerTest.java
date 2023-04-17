package collision;
/*
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.gameentity.Targettable;
import it.unibo.jumpig.model.impl.collision.CoinCollisionHandler;
import it.unibo.jumpig.model.impl.collision.EnemyCollisionHandler;
import it.unibo.jumpig.model.impl.gameentity.BasicCoin;
import it.unibo.jumpig.model.impl.gameentity.EnemyImpl;
import it.unibo.jumpig.model.impl.gameentity.PlayerImpl;*/

/**
 * Class to test correctness of subtypes of CollisionHandler.
 * {@link it.unibo.jumpig.model.api.collision.CollisionHandler}
 */
class CollisionHandlerTest {
/*
    private static final double PLAYER_POSITION_X = 5;
    private static final double PLAYER_POSITION_Y = 6.5;
    private static final double COIN_POSITION_X = 7;
    private static final double COIN_POSITION_Y = 7.5;
    private static final double ENEMY_POSITION_X = 6;
    private static final double ENEMY_POSITION_Y = 6.5;
    private static final Position PLAYER_POSITION = new PositionImpl(PLAYER_POSITION_X, PLAYER_POSITION_Y);
    private static final Position COIN_POSITION = new PositionImpl(COIN_POSITION_X, COIN_POSITION_Y);
    private static final Position ENEMY_POSITION = new PositionImpl(ENEMY_POSITION_X, ENEMY_POSITION_Y);

    static void assertIsTaken(final Targettable gameEntity) {
        assertTrue(gameEntity.isTaken());
    }

    @Test
    void testCoinCollision() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final var coin = new BasicCoin(COIN_POSITION, new CircleHitbox(COIN_POSITION, 3)); //TODO manca coinhitbox
        final var coinCollisionHandler = new CoinCollisionHandler();
        final double pickedCoins = player.getCoins();
        coinCollisionHandler.handle(player, coin);
        assertEquals(pickedCoins + 1, player.getCoins());
        assertIsTaken(coin);
    }

    @Test
    void testEnemyCollision() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final var enemy = new EnemyImpl(ENEMY_POSITION); //TODO manca EnemyHitbox
        final var enemyCollisionHandler = new EnemyCollisionHandler();
        final int playerLives = player.getLives();
        enemyCollisionHandler.handle(player, enemy);
        assertEquals(playerLives - 1, player.getLives());
        assertIsTaken(enemy);
    }*/
}
