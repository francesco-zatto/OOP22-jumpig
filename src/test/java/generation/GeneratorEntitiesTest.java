package generation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import it.unibo.jumpig.model.impl.GenerateCoinsStrategy;
import it.unibo.jumpig.model.impl.GenerateEnemiesStrategy;
import it.unibo.jumpig.model.impl.GeneratePlatformsStrategy;
import it.unibo.jumpig.model.impl.GeneratorEntitiesImpl;
import it.unibo.jumpig.model.impl.WorldImpl;
import it.unibo.jumpig.common.api.hitbox.Hitbox;
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
    private final GeneratorEntitiesImpl generator = new GeneratorEntitiesImpl(
        world.getWidth(), 
        world.getHeight()
        );
    private static final int NUM_BASIC_PLATFORM = 22;    /* The number of basic platforms */
    private static final int NUM_VANISHING_PLATFORM = 8;    /* The number of vanishing platforms */
    private static final int NUM_ENEMY = 2;    /* The number of enemies */
    private static final int NUM_COIN = 4;    /* The number of coins */
    private static final int NUM_BROKEN_PLATFORM = 5;    /* The number of broken platforms */

    /**
     * Method to check that all entitites are generated at a different height(different y).
     * @param <X> the type of the game entity
     * @param <H> the hitbox type
     * @param entities the entitites generated
     */
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
        this.generator.setGenerateStrategy(new GeneratePlatformsStrategy());
        final Set<Platform> setplatform = this.generator.generateEntities(this.world.getCamera());
        this.assertGeneration(setplatform);
    }

    @Test
    void testGenerationCoins() {
        this.generator.setGenerateStrategy(new GenerateCoinsStrategy());
        final Set<Coin> setcoin = this.generator.generateEntities(this.world.getCamera());
        this.assertGeneration(setcoin);
    }

    @Test
    void testGenerationEnemies() {
        this.generator.setGenerateStrategy(new GenerateEnemiesStrategy());
        final Set<Enemy> setenemy = this.generator.generateEntities(this.world.getCamera());
        this.assertGeneration(setenemy);
    }

    /**
     * The method to check the rightness of the number of generated entitites.
     */
    @Test
    void testNumberEntities() {
        assertEquals(
            world.getEntities().size(), 
            NUM_BASIC_PLATFORM + NUM_BROKEN_PLATFORM + NUM_COIN + NUM_ENEMY + NUM_VANISHING_PLATFORM + 1
        );  // +1 because in the set of entities there is also the player 
        this.generator.setGenerateStrategy(new GenerateCoinsStrategy());
        assertEquals(
            this.generator.generateEntities(this.world.getCamera()).size(), 
            NUM_COIN
        );
        this.generator.setGenerateStrategy(new GenerateEnemiesStrategy());
        assertEquals(
            this.generator.generateEntities(this.world.getCamera()).size(), 
            NUM_ENEMY
        );
        this.generator.setGenerateStrategy(new GeneratePlatformsStrategy());
        assertEquals(
            this.generator.generateEntities(this.world.getCamera()).size(), 
            NUM_BASIC_PLATFORM + NUM_BROKEN_PLATFORM + NUM_VANISHING_PLATFORM
        );
    }
}
