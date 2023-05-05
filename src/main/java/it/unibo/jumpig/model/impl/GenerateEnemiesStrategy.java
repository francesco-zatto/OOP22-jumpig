package it.unibo.jumpig.model.impl;

import java.util.Set;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.GeneratorEntitiesStrategy;
import it.unibo.jumpig.model.api.gameentity.GameEntity;

/**
 * The concrete strategy to generate enemies.
 */

public class GenerateEnemiesStrategy<H extends Hitbox, G extends GameEntity<H>> implements GeneratorEntitiesStrategy<H, G> {

    @Override
    public Set<G> generate() {
        return this.generateEnemies();
    }
    
    /**
     * The method to generate enemies.
     * @return a set of generated enemies.
     */
    private Set<G> generateEnemies(){
        return null;
    }
}
