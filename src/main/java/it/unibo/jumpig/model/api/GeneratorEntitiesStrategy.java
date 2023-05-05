package it.unibo.jumpig.model.api;

import java.util.Set;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Platform;

/**
 * The strategy interface to generate entities.
 */

public interface GeneratorEntitiesStrategy<H extends Hitbox, G extends GameEntity<H>> {

    /**
     * The refactored algorithm with the strategy pattern.
     * This is the method to generate the various set of entities. 
     * @return the generated set of game entity
     */
    Set<G> generate();

    /**
     * The method to generate platforms.
     * @return a set of generated platforms.
     */
    Set<Platform> generatePlatforms();

    /**
     * The method to generate enemies.
     * @return a set of generated enemies.
     */
    Set<Enemy> generateEnemies();

    /**
     * The method to generate coins.
     * @return a set of generated coins.
     */
    Set<Coin> generateCoins();
}
