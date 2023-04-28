package collision;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Targettable;
import it.unibo.jumpig.model.impl.gameentity.BasicCoin;
import it.unibo.jumpig.model.impl.gameentity.EnemyImpl;
import it.unibo.jumpig.model.impl.gameentity.PlayerImpl;

/**
 * Class to test correctness of subtypes of CollisionHandler.
 * {@link it.unibo.jumpig.model.api.collision.CollisionHandler}
 */
class CollisionHandlerTest {

    private static final double PLAYER_POSITION_X = 5;
    private static final double PLAYER_POSITION_Y = 6.5;
    private static final double COIN_POSITION_X = 7;
    private static final double COIN_POSITION_Y = 7.5;
    private static final double ENEMY_POSITION_X = 6;
    private static final double ENEMY_POSITION_Y = 6.5;
    private static final int NUMBER_OF_COINS = 10;
    private static final Position PLAYER_POSITION = new PositionImpl(PLAYER_POSITION_X, PLAYER_POSITION_Y);
    private static final Position COIN_POSITION = new PositionImpl(COIN_POSITION_X, COIN_POSITION_Y);
    private static final Position ENEMY_POSITION = new PositionImpl(ENEMY_POSITION_X, ENEMY_POSITION_Y);

    static void assertIsTaken(final Targettable gameEntity) {
        assertTrue(gameEntity.isTaken());
    }

    @Test
    void testCoinCollision() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final var coin = new BasicCoin(COIN_POSITION);
        final double pickedCoins = player.getCoins();
        coin.handleCollision(player);
        assertEquals(pickedCoins + 1, player.getCoins());
        assertIsTaken(coin);
    }

    @Test
    void testManyCoinCollisions() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final List<? extends Coin> coins = Stream.iterate(0, i -> i + 1)
                .limit(NUMBER_OF_COINS)
                .map(i -> COIN_POSITION)
                .map(BasicCoin::new)
                .toList();
        final double pickedCoins = player.getCoins();
        coins.forEach(c -> c.handleCollision(player));
        assertEquals(pickedCoins + NUMBER_OF_COINS, player.getCoins());
        coins.forEach(CollisionHandlerTest::assertIsTaken);
    }

    @Test
    void testCoinIsAlreadyPicked() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final var coin = new BasicCoin(COIN_POSITION);
        coin.handleCollision(player);
        final double pickedCoins = player.getCoins();
        coin.handleCollision(player);
        assertEquals(pickedCoins, player.getCoins());
    }

    @Test
    void testEnemyCollision() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final var enemy = new EnemyImpl(ENEMY_POSITION);
        final int playerLives = player.getLives();
        enemy.handleCollision(player);
        assertEquals(playerLives - 1, player.getLives());
        assertIsTaken(enemy);
    }
}
