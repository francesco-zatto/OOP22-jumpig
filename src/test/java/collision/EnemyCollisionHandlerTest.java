package collision;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.impl.gameentity.EnemyImpl;
import it.unibo.jumpig.model.impl.gameentity.PlayerImpl;

class EnemyCollisionHandlerTest {

    private static final double PLAYER_POSITION_X = 5;
    private static final double PLAYER_POSITION_Y = 6.5;
    private static final double ENEMY_POSITION_X = 6;
    private static final double ENEMY_POSITION_Y = 6.5;
    private static final Position PLAYER_POSITION = new PositionImpl(PLAYER_POSITION_X, PLAYER_POSITION_Y);
    private static final Position ENEMY_POSITION = new PositionImpl(ENEMY_POSITION_X, ENEMY_POSITION_Y);

    @Test
    void testEnemyCollision() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final var enemy = new EnemyImpl(ENEMY_POSITION);
        final int playerLives = player.getLives();
        enemy.handleCollision(player);
        assertEquals(playerLives - 1, player.getLives());
        CoinCollisionHandlerTest.assertIsTaken(enemy);
    }

    @Test
    void testManyEnemyCollisions() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final List<? extends Enemy> enemies = Stream.iterate(0, i -> i + 1)
                .limit(player.getLives())
                .map(i -> ENEMY_POSITION)
                .map(EnemyImpl::new)
                .toList();
        enemies.forEach(e -> e.handleCollision(player));
        enemies.forEach(CoinCollisionHandlerTest::assertIsTaken);
        assertEquals(0, player.getLives());
    }
}
