package it.unibo.jumpig.model.api;

import java.util.Set;

import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.Platform;

/**
 * The interface for a generator of entities.
 */

public interface GeneratorEntities {

    /**
     * The method to generate platforms.
     */
    Set<Platform> generatePlatforms();

    /**
     * The method to generate enemies.
     */
    Set<Enemy> generateEnemies();

    /**
     * The method to generate coins.
     */
    Set<Coin> generateCoins();
}
