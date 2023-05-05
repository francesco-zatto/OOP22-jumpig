package it.unibo.jumpig.model.impl;

import java.util.Set;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.GeneratorEntitiesStrategy;
import it.unibo.jumpig.model.api.gameentity.GameEntity;

/**
 * The concrete strategy to generate coins.
 */

public class GenerateCoinsStrategy<H extends Hitbox, G extends GameEntity<H>> implements GeneratorEntitiesStrategy<H, G>{

    @Override
    public Set<G> generate() {
        return this.generateCoins();
    }
    
    /**
     * The method to generate coins.
     * @return a set of generated coins.
     */
    private Set<G> generateCoins(){
        return null;
    }
}
