package gameentity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.jumpig.common.api.Position;
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

    @Test
    void testPlayerPosition() {
        final Position finalPosition = new PositionImpl(PLAYER_COMPONENT_X, PLAYER_COMPONENT_Y);
        player.setVelocityFromJump(new VelocityImpl(PLAYER_COMPONENT_X, PLAYER_COMPONENT_Y));
        player.computePosition(TIME);
        assertEquals(player.getPosition().getX(), 
            finalPosition.getX() * 2, () -> "testPlayerPosition failed");
        assertEquals(player.getPosition().getY(), 
            finalPosition.getY() * 2, () -> "testPlayerPosition failed");
    }

    @Test 
    void testPlayerVelocity() {
        final Velocity playerVelocity = 
        new VelocityImpl(finalVelocity.getXComponent(), finalVelocity.getYComponent());
        player.setVelocityFromJump(playerVelocity);
        player.computeVelocity(GRAVITY, TIME, 0);
        finalVelocity.computeAcceleratedVelocity(GRAVITY, TIME);
        assertEquals(finalVelocity.getModule(), 
            player.getVelocity().getModule(), () -> "testPlayerVelocity failed");
    }
}
