package collision;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.collision.CollisionHandlerFactory;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.collision.CollisionHandlerFactoryImpl;
import it.unibo.jumpig.model.impl.gameentity.BasicPlatform;
import it.unibo.jumpig.model.impl.gameentity.PlayerImpl;

/**
 * Class to test correctness of collisionHandlers created by CollisionHandlerFactory.
 * {@link it.unibo.jumpig.model.api.collision.CollisionHandler} 
 * {@link it.unibo.jumpig.model.api.collision.CollisionHandlerFactory}
 */
class CollisionHandlerTest {

    private static final double PLATFORM_POSITION_X = 5;
    private static final double PLATFORM_POSITION_Y = 5;
    private static final double PLAYER_POSITION_X = 5;
    private static final double PLAYER_POSITION_Y = 10;
    private static final double PLATFORM_VELOCITY = 10;
    private static final Position PLATFORM_POSITION = new PositionImpl(PLATFORM_POSITION_X, PLATFORM_POSITION_Y);
    private static final Position PLAYER_POSITION = new PositionImpl(PLAYER_POSITION_X, PLAYER_POSITION_Y);
    
    private final Player player = new PlayerImpl(PLAYER_POSITION);
    private final CollisionHandlerFactory collisionHandlerFactory = new CollisionHandlerFactoryImpl();

    @Test
    void testBasicPlatformCollisionHandler() {
       final Platform platform = new BasicPlatform(PLATFORM_POSITION, PLATFORM_VELOCITY);
       final var platformCollisionHandler = this.collisionHandlerFactory.createPlatformCollisionHandler();
       platformCollisionHandler.handle(player, platform);
       assertEquals(platform.getJumpVelocity().getYComponent(), this.player.getVelocity().getYComponent());
    }
}
