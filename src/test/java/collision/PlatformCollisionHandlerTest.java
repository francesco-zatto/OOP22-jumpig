package collision;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.gameentity.BasicPlatform;
import it.unibo.jumpig.model.impl.gameentity.BrokenPlatform;
import it.unibo.jumpig.model.impl.gameentity.PlayerImpl;
import it.unibo.jumpig.model.impl.gameentity.VanishingPlatform;

/**
 * Class to test correctness of CollisionHandlers for collisions only with platforms.
 * {@link it.unibo.jumpig.model.impl.collision.BasicPlatformCollisionHandler}
 * {@link it.unibo.jumpig.model.impl.collision.VanishingPlatformCollisionHandler}
 */
class PlatformCollisionHandlerTest {

    private static final double PLATFORM_POSITION_X = 5;
    private static final double PLATFORM_POSITION_Y = 5;
    private static final double PLAYER_POSITION_X = 5;
    private static final double PLAYER_POSITION_Y = 6.5;
    private static final double PLATFORM_VELOCITY = 10;
    private static final double DELTA_TIME = 0.01;
    private static final double GRAVITY = -9.81;
    private static final Position PLATFORM_POSITION = new PositionImpl(PLATFORM_POSITION_X, PLATFORM_POSITION_Y);
    private static final Position PLAYER_POSITION = new PositionImpl(PLAYER_POSITION_X, PLAYER_POSITION_Y);

    private static void assertCollision(final Player player, final Platform platform) {
        assertEquals(platform.getJumpVelocity().getYComponent(), player.getVelocity().getYComponent());
        assertTrue(player.getLastPlatformHeight().isPresent());
        assertEquals(player.getLastPlatformHeight().get(), platform.getPosition().getY());
    }

    @Test
    void testBasicPlatformCollision() {
       final var player = new PlayerImpl(PLAYER_POSITION);
       final var platform = new BasicPlatform(PLATFORM_POSITION, PLATFORM_VELOCITY);
       player.computeVelocity(GRAVITY, DELTA_TIME, 0);
       platform.handleCollision(player);
       assertCollision(player, platform);
    }

    @Test
    void testPlayerIsNotGoingDown() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final var platform = new BasicPlatform(PLATFORM_POSITION, PLATFORM_VELOCITY);
        platform.handleCollision(player);
        /* The player jumps on a platform only if he is going down, i.e yComponent is negative.*/
        assertTrue(player.getVelocity().getYComponent() >= 0);
        assertNotEquals(player.getVelocity().getYComponent(), platform.getJumpVelocity().getYComponent());
    }

    @Test
    void testVanishingPlatformCollision() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final var platform = new VanishingPlatform(PLATFORM_POSITION, PLATFORM_VELOCITY);
        player.computeVelocity(GRAVITY, DELTA_TIME, 0);
        platform.handleCollision(player);
        assertCollision(player, platform);
        CollisionHandlerTest.assertIsTaken(platform);
    }

    @Test
    void testVanishingPlatformIsAlreadyTaken() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final var platform = new VanishingPlatform(PLATFORM_POSITION, PLATFORM_VELOCITY);
        player.computeVelocity(GRAVITY, DELTA_TIME, 0);
        platform.handleCollision(player);
        /* 
         * After a collision the player moves in the y axis with a decelerated movement, so the next line
         * compute the time of fall before that the player is exactly in the position where he has just collided with
         * the vanishing platform. But this time he will not jumps, because the platform is vanished.
         */
        final double fallingTime = 2 * player.getVelocity().getYComponent() / -GRAVITY;
        player.computeVelocity(GRAVITY, fallingTime, 0);
        platform.handleCollision(player);
        CollisionHandlerTest.assertIsTaken(platform);
        assertEquals(platform.getJumpVelocity().getYComponent(), -player.getVelocity().getYComponent());
    }

    @Test
    void testBrokenPlatformCollision() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final var platform = new BrokenPlatform(PLATFORM_POSITION);
        player.computeVelocity(GRAVITY, DELTA_TIME, 0);
        final var playerVerticalVelocityBeforeCollision = player.getVelocity().getYComponent();
        platform.handleCollision(player);
        /*
         * This assert checks that the velocity of the player is not changed because of the collision of a broken platform,
         * that the player should not jump on it, but only break it.
         */
        assertEquals(playerVerticalVelocityBeforeCollision, player.getVelocity().getYComponent());
        CollisionHandlerTest.assertIsTaken(platform);
    }
}
