
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import it.unibo.jumpig.model.impl.GeneratorEntitiesImpl;
import it.unibo.jumpig.model.api.GeneratorEntities;
import it.unibo.jumpig.model.api.gameentity.GameEntity;


/**
 * The class to test the correctness of GeneratorEntitiesImpl.
 * {@link it.unibo.jumpig.model.impl.GeneratorEntitiesImpl} 
 */

class GeneratorEntitiesTest { //NOPMD

    private final GeneratorEntities generator = new GeneratorEntitiesImpl();

    private <X extends GameEntity> void assertGeneration(final Stream<X> entities) {
        while (entities.iterator().hasNext()) {
            final var next = entities.iterator().next();
            assertTrue(entities.filter(x -> x != next).allMatch(x -> x.getPosition().getY() != next.getPosition().getY()));
        }
    }

    @Test
    void testGenerationPlatforms() {
        final var setplatform = this.generator.generatePlatforms();
        this.assertGeneration(setplatform.stream());
    }

}
