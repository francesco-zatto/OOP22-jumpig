package gameentity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.Direction;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.Velocity;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.VelocityImpl;
import it.unibo.jumpig.model.impl.gameentity.PlayerImpl;

/**
 * Class to test the correctness of PlayerImpl.
 * {@link it.unibo.jumpig.model.impl.gameentity.PlayerImpl}
 */
class PlayerTest {

    private static final double PLAYER_COMPONENT_X = 5;
    private static final double PLAYER_COMPONENT_Y = 5;
    private final Velocity finalVelocity = new VelocityImpl(0, 10);
    private static final double GRAVITY = 10.0;
    private static final double TIME = 2.0;
    private final Player player = new PlayerImpl(new PositionImpl(0, 0));
    private final Direction direction = Direction.HORIZONTAL_ZERO;

    @Test
    void testPlayerPosition() {
        final Position finalPosition = new PositionImpl(PLAYER_COMPONENT_X, PLAYER_COMPONENT_Y);
        this.player.setVelocityFromJump(new VelocityImpl(PLAYER_COMPONENT_X, PLAYER_COMPONENT_Y));
        this.player.computePosition(TIME);
        assertEquals(this.player.getPosition().getX(), 
            finalPosition.getX() * 2, () -> "testPlayerPosition failed");
        assertEquals(player.getPosition().getY(), 
            finalPosition.getY() * 2, () -> "testPlayerPosition failed");
    }

    @Test 
    void testPlayerVelocity() {
        final Velocity playerVelocity = 
        new VelocityImpl(finalVelocity.getXComponent(), finalVelocity.getYComponent());
        this.player.setVelocityFromJump(playerVelocity);
        this.player.computeVelocity(GRAVITY, TIME, direction);
        finalVelocity.computeAcceleratedVelocity(GRAVITY, TIME);
        assertEquals(finalVelocity.getModule(), 
            player.getVelocity().getModule(), () -> "testPlayerVelocity failed");
    }

    @Test
    void testCoins() {
        this.player.incrementCoins();
        this.player.incrementCoins();
        this.player.incrementCoins();
        assertEquals(3, this.player.getCoins());
    }

    @Test
    void testLives() {
        this.player.decreaseLives();
        assertTrue(isPlayerAlive());
        this.player.decreaseLives();
        assertTrue(isPlayerAlive());
        this.player.decreaseLives();
        assertFalse(isPlayerAlive());
    }

    private boolean isPlayerAlive() {
        return this.player.getLives() > 0 ? true : false;
    }
}
