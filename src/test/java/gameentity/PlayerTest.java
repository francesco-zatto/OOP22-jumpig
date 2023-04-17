package gameentity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.impl.VelocityImpl;
import it.unibo.jumpig.model.impl.gameentity.PlayerImpl;

/**
 * Class to test the correctness of PlayerImpl.
 * {@link it.unibo.jumpig.model.impl.gameentity.PlayerImpl}
 */
class PlayerTest {

    private static final double PLAYER_COMPONENT_X = 5;
    private static final double PLAYER_COMPONENT_Y = 5;
    private final PlayerImpl player = new PlayerImpl(new PositionImpl(0, 0));

    @Test
    void testPlayerPosition() {
        final Position finalPosition = new PositionImpl(PLAYER_COMPONENT_X, PLAYER_COMPONENT_Y);
        player.setVelocityFromJump(new VelocityImpl(PLAYER_COMPONENT_X, PLAYER_COMPONENT_Y));
        player.computePosition(1);
        assertEquals(player.getPosition().getX(), finalPosition.getX());
        assertEquals(player.getPosition().getY(), finalPosition.getY());
    }
}
