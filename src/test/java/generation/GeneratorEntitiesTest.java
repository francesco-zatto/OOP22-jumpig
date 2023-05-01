package generation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import it.unibo.jumpig.model.impl.GeneratorEntitiesImpl;
import it.unibo.jumpig.model.impl.WorldImpl;
import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.GeneratorEntities;
import it.unibo.jumpig.model.api.World;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Enemy;

/**
 * The class to test the correctness of GeneratorEntitiesImpl.
 * {@link it.unibo.jumpig.model.impl.GeneratorEntitiesImpl} 
 */

class GeneratorEntitiesTest {

    private final World world = new WorldImpl();
    private final GeneratorEntities generator = new GeneratorEntitiesImpl(world.getWidth(), world.getHeight(), world.getCamera());
    private static final int NUM_BASIC_PLATFORM = 8;    /* The number of basic platforms */
    private static final int NUM_VANISHING_PLATFORM = 4;    /* The number of vanishing platforms */
    private static final int NUM_ENEMY = 2;    /* The number of enemies */
    private static final int NUM_COIN = 5;    /* The number of coins */
    private static final int NUM_BROKEN_PLATFORM = 2;    /* The number of broken platforms */

    private <X extends GameEntity<H>, H extends Hitbox> void assertGeneration(final Set<X> entities) {
        for (int i = 0; i < entities.size(); i++) {
            final X next = entities.stream()
                    .toList()
                    .get(i);
            assertTrue(
                    entities.stream()
                    .skip(i + 1)
                    .allMatch(x -> x.getPosition().getY() != next.getPosition().getY())
            );
        }
    }

    @Test
    void testGenerationPlatforms() {
        final Set<Platform> setplatform = this.generator.generatePlatforms();
        this.assertGeneration(setplatform);
    }

    @Test
    void testGenerationCoins() {
        final Set<Coin> setcoin = this.generator.generateCoins();
        this.assertGeneration(setcoin);
    }

    @Test
    void testGenerationEnemies() {
        final Set<Enemy> setenemy = this.generator.generateEnemies();
        this.assertGeneration(setenemy);
    }

    @Test
    void testNumberEntities() {
        assertEquals(
            world.getEntities().size(), 
            NUM_BASIC_PLATFORM + NUM_BROKEN_PLATFORM + NUM_COIN + NUM_ENEMY + NUM_VANISHING_PLATFORM + 1
        );  // +1 because in the set of entities there is also the player 
        assertEquals(
            this.generator.generateCoins().size(), 
            NUM_COIN
        );
        assertEquals(
            this.generator.generateEnemies().size(), 
            NUM_ENEMY
        );
        assertEquals(
            this.generator.generatePlatforms().size(), 
            NUM_BASIC_PLATFORM + NUM_BROKEN_PLATFORM + NUM_VANISHING_PLATFORM
        );
    }
}
