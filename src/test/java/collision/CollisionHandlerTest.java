package collision;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.gameentity.Targettable;
import it.unibo.jumpig.model.impl.collision.BasicPlatformCollisionHandler;
import it.unibo.jumpig.model.impl.collision.CoinCollisionHandler;
import it.unibo.jumpig.model.impl.collision.EnemyCollisionHandler;
import it.unibo.jumpig.model.impl.collision.VanishingPlatformCollisionHandler;
import it.unibo.jumpig.model.impl.gameentity.BasicCoin;
import it.unibo.jumpig.model.impl.gameentity.BasicPlatform;
import it.unibo.jumpig.model.impl.gameentity.EnemyImpl;
import it.unibo.jumpig.model.impl.gameentity.PlayerImpl;
import it.unibo.jumpig.model.impl.gameentity.VanishingPlatform;

/**
 * Class to test correctness of subtypes of CollisionHandler.
 * {@link it.unibo.jumpig.model.api.collision.CollisionHandler}
 */
class CollisionHandlerTest {

    private static final double PLATFORM_POSITION_X = 5;
    private static final double PLATFORM_POSITION_Y = 5;
    private static final double PLAYER_POSITION_X = 5;
    private static final double PLAYER_POSITION_Y = 6.5;
    private static final double COIN_POSITION_X = 7;
    private static final double COIN_POSITION_Y = 7.5;
    private static final double ENEMY_POSITION_X = 6;
    private static final double ENEMY_POSITION_Y = 6.5;
    private static final double PLATFORM_VELOCITY = 10;
    private static final double DELTA_TIME = 0.0001;
    private static final double GRAVITY = -9.81;
    private static final Position PLATFORM_POSITION = new PositionImpl(PLATFORM_POSITION_X, PLATFORM_POSITION_Y);
    private static final Position PLAYER_POSITION = new PositionImpl(PLAYER_POSITION_X, PLAYER_POSITION_Y);
    private static final Position COIN_POSITION = new PositionImpl(COIN_POSITION_X, COIN_POSITION_Y);
    private static final Position ENEMY_POSITION = new PositionImpl(ENEMY_POSITION_X, ENEMY_POSITION_Y);

    private void assertIsTaken(final Targettable gameEntity) {
        assertTrue(gameEntity.isTaken());
    }

    @Test
    void testBasicPlatformCollisionHandler() {
       final var player = new PlayerImpl(PLAYER_POSITION);
       final var platform = new BasicPlatform(PLATFORM_POSITION, PLATFORM_VELOCITY);
       final var platformCollisionHandler = new BasicPlatformCollisionHandler();
       player.computeVelocity(GRAVITY, DELTA_TIME);
       platformCollisionHandler.handle(player, platform);
       assertEquals(platform.getJumpVelocity().getYComponent(), player.getVelocity().getYComponent());
    }

    @Test
    void testVanishingPlatformCollisionHandler() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final var platform = new VanishingPlatform(PLATFORM_POSITION, PLATFORM_VELOCITY);
        final var platformCollisionHandler = new VanishingPlatformCollisionHandler();
        player.computeVelocity(GRAVITY, DELTA_TIME);
        platformCollisionHandler.handle(player, platform);
        assertEquals(platform.getJumpVelocity().getYComponent(), player.getVelocity().getYComponent());
        this.assertIsTaken(platform);
    }

    @Test
    void testCoinCollisionHandler() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final var coin = new BasicCoin(COIN_POSITION, new CircleHitbox(COIN_POSITION, 3));
        final var coinCollisionHandler = new CoinCollisionHandler();
        final double pickedCoins = player.getCoins();
        coinCollisionHandler.handle(player, coin);
        assertEquals(pickedCoins + 1, player.getCoins());
        this.assertIsTaken(coin);
    }

    @Test
    void testEnemyCollisionHandler() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final var enemy = new EnemyImpl(ENEMY_POSITION, new RectangleHitbox(ENEMY_POSITION, 5, 6));
        final var enemyCollisionHandler = new EnemyCollisionHandler();
        final int playerLives = player.getLives();
        enemyCollisionHandler.handle(player, enemy);
        assertEquals(playerLives - 1, player.getLives());
        this.assertIsTaken(enemy);
    }
}
