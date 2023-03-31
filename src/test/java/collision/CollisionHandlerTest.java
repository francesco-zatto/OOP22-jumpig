package collision;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.model.api.collision.CollisionHandlerFactory;
import it.unibo.jumpig.model.api.gameentity.Targettable;
import it.unibo.jumpig.model.impl.collision.CollisionHandlerFactoryImpl;
import it.unibo.jumpig.model.impl.gameentity.BasicCoin;
import it.unibo.jumpig.model.impl.gameentity.BasicPlatform;
import it.unibo.jumpig.model.impl.gameentity.PlayerImpl;
import it.unibo.jumpig.model.impl.gameentity.VanishingPlatform;

/**
 * Class to test correctness of collisionHandlers created by CollisionHandlerFactory.
 * {@link it.unibo.jumpig.model.api.collision.CollisionHandler} 
 * {@link it.unibo.jumpig.model.api.collision.CollisionHandlerFactory}
 */
class CollisionHandlerTest {

    private static final double PLATFORM_POSITION_X = 5;
    private static final double PLATFORM_POSITION_Y = 5;
    private static final double PLAYER_POSITION_X = 5;
    private static final double PLAYER_POSITION_Y = 6.5;
    private static final double COIN_POSITION_X = 7;
    private static final double COIN_POSITION_Y = 7.5;
    private static final double PLATFORM_VELOCITY = 10;
    private static final double DELTA_TIME = 0.0001;
    private static final double GRAVITY = -9.81;
    private static final Position PLATFORM_POSITION = new PositionImpl(PLATFORM_POSITION_X, PLATFORM_POSITION_Y);
    private static final Position PLAYER_POSITION = new PositionImpl(PLAYER_POSITION_X, PLAYER_POSITION_Y);
    private static final Position COIN_POSITION = new PositionImpl(COIN_POSITION_X, COIN_POSITION_Y);
    private final CollisionHandlerFactory collisionHandlerFactory = new CollisionHandlerFactoryImpl();

    private void assertIsTaken(final Targettable gameEntity) {
        assertTrue(gameEntity.isTaken());
    }

    @Test
    void testBasicPlatformCollisionHandler() {
       final var player = new PlayerImpl(PLAYER_POSITION);
       final var platform = new BasicPlatform(PLATFORM_POSITION, PLATFORM_VELOCITY);
       final var platformCollisionHandler = this.collisionHandlerFactory.createPlatformCollisionHandler();
       player.computeVelocity(GRAVITY, DELTA_TIME);
       platformCollisionHandler.handle(player, platform);
       assertEquals(platform.getJumpVelocity().getYComponent(), player.getVelocity().getYComponent());
    }

    @Test
    void testVanishingPlatformCollisionHandler() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final var platform = new VanishingPlatform(PLATFORM_POSITION, PLATFORM_VELOCITY);
        final var platformCollisionHandler = this.collisionHandlerFactory.createPlatformCollisionHandler();
        player.computeVelocity(GRAVITY, DELTA_TIME);
        //TO DO fix Targettable and VenishingPlatform
        platform.setTarget(false);
        platformCollisionHandler.handle(player, platform);
        assertEquals(platform.getJumpVelocity().getYComponent(), player.getVelocity().getYComponent());
        assertIsTaken(platform);
    }

    @Test
    void testCoinCollisionHandler() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final var coin = new BasicCoin(COIN_POSITION, new CircleHitbox(COIN_POSITION, 3));
        final var coinCollisionHandler = this.collisionHandlerFactory.createCoinCollisionHandler();
        final double pickedCoins = player.getCoins();
        coinCollisionHandler.handle(player, coin);
        assertEquals(pickedCoins + 1, player.getCoins());
        assertIsTaken(coin);
    }

    @Test
    void testEnemyCollisionHandler() {
    }
}
