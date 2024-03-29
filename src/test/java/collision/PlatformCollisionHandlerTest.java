package collision;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.Direction;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.common.impl.hitbox.PlatformHitbox;
import it.unibo.jumpig.common.impl.hitbox.PlayerHitbox;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.WorldImpl;
import it.unibo.jumpig.model.impl.gameentity.BasicPlatform;
import it.unibo.jumpig.model.impl.gameentity.BrokenPlatform;
import it.unibo.jumpig.model.impl.gameentity.PlayerImpl;
import it.unibo.jumpig.model.impl.gameentity.VanishingPlatform;

/**
 * Class to test correctness of CollisionHandlers for collisions only with platforms.
 * {@link it.unibo.jumpig.model.impl.collision.BasicPlatformCollisionChecker}
 * {@link it.unibo.jumpig.model.impl.collision.PlatformCollisionActioner}
 * {@link it.unibo.jumpig.model.impl.collision.TargettablePlatformCollisionChecker}
 * {@link it.unibo.jumpig.model.impl.collision.VanishingPlatformCollisionActioner}
 * {@link it.unibo.jumpig.model.impl.collision.BrokenPlatformCollisionActioner}
 */
class PlatformCollisionHandlerTest {

    private static final double PLAYER_POSITION_X = 5;
    private static final double PLAYER_POSITION_Y = 6.5;
    private static final double PLATFORM_VELOCITY = 10;
    private static final double DELTA_TIME = 0.05;
    private static final double GRAVITY = new WorldImpl().getGravity();
    private static final Position STARTING_POSITION = new PositionImpl(PLAYER_POSITION_X, PLAYER_POSITION_Y);
    private static final double HALF_PLATFORM_HEIGHT = new PlatformHitbox(STARTING_POSITION).getHeight() / 2;
    /*
     * This position of the platform is under the player in the first position when the test starts.
    */
    private static final Position PLATFORM_UNDER_PLAYER_POSITION = new PositionImpl(
        PLAYER_POSITION_X, 
        new PlayerHitbox(STARTING_POSITION).getLowerY() - HALF_PLATFORM_HEIGHT
    );

    private static void assertCollision(final Player player, final Platform platform) {
        assertEquals(platform.getJumpVelocity().getYComponent(), player.getVelocity().getYComponent());
        assertTrue(player.getLastPlatformHeight().isPresent());
        assertEquals(player.getLastPlatformHeight().get(), platform.getPosition().getY());
    }

    /*
     * This method asserts that playerBottomY is in the right position for a possible jump on the platform. Only ordinates
     * should be checked, because in this tests platforms and players have the same abscissas.
     */
    private static void assertInJumpingRange(final Player player, final Platform platform) {
        final var playerLowerY = player.getHitbox().getLowerY();
        assertTrue(playerLowerY > platform.getPosition().getY());
        assertTrue(playerLowerY < platform.getHitbox().getUpperY());
    }

    private double computeFallingTime(final Player player) {
        return 2 * player.getVelocity().getYComponent() / -GRAVITY;
    }

    private void computeMovement(final PlayerImpl player, final double collisionTime) {
        Stream.iterate(0.0, t -> t < collisionTime, t -> t + DELTA_TIME)
                .map(t -> DELTA_TIME)
                .forEach(dt -> {
                    player.computeVelocity(GRAVITY, dt, Direction.HORIZONTAL_ZERO); 
                    player.computePosition(dt);
                });
    }

    @Test
    void testBasicPlatformCollision() {
        final var player = new PlayerImpl(STARTING_POSITION);
        final var platform = new BasicPlatform(PLATFORM_UNDER_PLAYER_POSITION, PLATFORM_VELOCITY);
        final double collisionTime = computeFallingTime(player) - DELTA_TIME;
        computeMovement(player, collisionTime);
        platform.handleCollision(player);
        assertCollision(player, platform);
    }

    @Test
    void testPlayerIsNotGoingDown() {
        final var player = new PlayerImpl(STARTING_POSITION);
        final var platform = new BasicPlatform(PLATFORM_UNDER_PLAYER_POSITION, PLATFORM_VELOCITY);
        platform.handleCollision(player);
        /* The player jumps on a platform only if he is going down, i.e yComponent is negative.*/
        assertTrue(player.getVelocity().getYComponent() >= 0);
        assertNotEquals(player.getVelocity().getYComponent(), platform.getJumpVelocity().getYComponent());
    }

    @Test
    void testVanishingPlatformCollision() {
        final var player = new PlayerImpl(STARTING_POSITION);
        final var platform = new VanishingPlatform(PLATFORM_UNDER_PLAYER_POSITION, PLATFORM_VELOCITY);
        final double collisionTime = computeFallingTime(player) - DELTA_TIME;
        computeMovement(player, collisionTime);
        platform.handleCollision(player);
        assertCollision(player, platform);
        CoinCollisionHandlerTest.assertIsTaken(platform);
    }

    @Test
    void testVanishingPlatformIsAlreadyTaken() {
        final var player = new PlayerImpl(STARTING_POSITION);
        final var platform = new VanishingPlatform(PLATFORM_UNDER_PLAYER_POSITION, PLATFORM_VELOCITY);
        final double collisionTime = computeFallingTime(player) - DELTA_TIME;
        computeMovement(player, collisionTime);
        platform.handleCollision(player);
        /* 
         * After a collision the player moves in the y axis with a decelerated movement, so the next line
         * compute the time of fall before that the player is exactly in the position where he has just collided with
         * the vanishing platform. But this time he will not jump, because the platform is vanished.
         */
        final double secondFallingTime = computeFallingTime(player);
        computeMovement(player, secondFallingTime);
        platform.handleCollision(player);
        CoinCollisionHandlerTest.assertIsTaken(platform);
        assertNotEquals(platform.getJumpVelocity().getYComponent(), player.getVelocity().getYComponent());
        assertInJumpingRange(player, platform);
    }

    @Test
    void testBrokenPlatformCollision() {
        final var player = new PlayerImpl(STARTING_POSITION);
        final var platform = new BrokenPlatform(PLATFORM_UNDER_PLAYER_POSITION);
        player.computeVelocity(GRAVITY, DELTA_TIME, Direction.HORIZONTAL_ZERO); 
        final double collisionTime = computeFallingTime(player) - DELTA_TIME;
        computeMovement(player, collisionTime);
        platform.handleCollision(player);
        /*
         * This assert checks that the velocity of the player is not changed because of the collision of a broken platform,
         * that the player should not jump on it, but only break it.
         */
        assertNotEquals(player.getVelocity().getYComponent(), platform.getJumpVelocity());
        CoinCollisionHandlerTest.assertIsTaken(platform);
        assertInJumpingRange(player, platform);
    }
}
