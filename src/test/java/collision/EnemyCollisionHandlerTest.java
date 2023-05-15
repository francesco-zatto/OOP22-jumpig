package collision;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.impl.gameentity.EnemyImpl;
import it.unibo.jumpig.model.impl.gameentity.PlayerImpl;

/**
 * Class to test correctness of subtypes of CollisionHandler for enemies.
 * {@link it.unibo.jumpig.model.api.collision.CollisionHandler}
 */
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
        final List<? extends Enemy> enemies = createEnemies(player.getLives(), () -> ENEMY_POSITION);
        enemies.forEach(e -> e.handleCollision(player));
        enemies.forEach(CoinCollisionHandlerTest::assertIsTaken);
        assertEquals(0, player.getLives());
    }

    @Test
    void testManyEnemyCollisionsInDifferentPositions() {
        final var player = new PlayerImpl(PLAYER_POSITION);
        final int playerLives = player.getLives();
        final int numberOfEnemies = (int) (player.getHitbox().getHeight());
        final List<? extends Enemy> enemies = createEnemies(numberOfEnemies, insidePlayerPositions(player.getHitbox()));
        enemies.forEach(e -> e.handleCollision(player));
        enemies.forEach(CoinCollisionHandlerTest::assertIsTaken);
        assertEquals(playerLives - numberOfEnemies, player.getLives());
    }

    private Supplier<Position> insidePlayerPositions(final RectangleHitbox player) {
        return new Supplier<>() {

            private double enemyOrdinate = player.getLowerY();

            @Override
            public Position get() {
                return new PositionImpl(player.getCenter().getX(), this.enemyOrdinate++);
            }
        };
    }

    private List<? extends Enemy> createEnemies(final int numberOfEnemies, final Supplier<Position> positionSupplier) {
        return Stream.generate(positionSupplier)
                .limit(numberOfEnemies)
                .map(EnemyImpl::new)
                .toList();
    }
}
