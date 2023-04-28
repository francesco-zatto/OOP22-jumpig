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
    final Velocity finalVelocity = new VelocityImpl(0,10);
    private static final double gravity = 10;
    private static final double deltaTime = 2;
    private final Player player = new PlayerImpl(new PositionImpl(0, 0));

    @Test
    void testPlayerPosition() {
        final Position finalPosition = new PositionImpl(PLAYER_COMPONENT_X, PLAYER_COMPONENT_Y);
        player.setVelocityFromJump(new VelocityImpl(PLAYER_COMPONENT_X, PLAYER_COMPONENT_Y));
        player.computePosition(deltaTime);
        assertEquals(player.getPosition().getX(), finalPosition.getX() * 2);
        assertEquals(player.getPosition().getY(), finalPosition.getY() * 2);
    }

    @Test 
    void testPlayerVelocity() {
        final Velocity playerVelocity = finalVelocity;
        player.setVelocityFromJump(playerVelocity);
        System.out.println(player.getVelocity().getYComponent());
        System.out.println(finalVelocity.getYComponent());
        player.computeVelocity(gravity, deltaTime);
        System.out.println(finalVelocity.getYComponent());       
        System.out.println(player.getVelocity().getYComponent());
        assertEquals(finalVelocity.getModule(), player.getVelocity().getYComponent());
    }
}
