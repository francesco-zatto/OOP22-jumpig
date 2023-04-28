package generation;

import org.junit.jupiter.api.Test;
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

    private <X extends GameEntity<H>, H extends Hitbox> void assertGeneration(final Set<X> entities) {
        for (int i = 0; i < entities.size(); i++) {
            final X next = entities.stream().toList().get(i);
            assertTrue(entities.stream().skip(i + 1).allMatch(x -> x.getPosition().getY() != next.getPosition().getY()));
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
}
