package it.unibo.jumpig.model.impl;

import java.util.Set;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.GeneratorEntitiesStrategy;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Platform;

/**
 * The concrete strategy to generate platforms.
 */

public class GeneratePlatformsStrategy<H extends Hitbox, G extends GameEntity<H>> implements GeneratorEntitiesStrategy<H, G> {

    @Override
    public Set<G> generate() {
        return this.generatePlatforms();
    }
    
    /**
     * The method to generate platforms.
     * @return a set of generated platforms.
     */
    private Set<G> generatePlatforms(){
        return null;
    }

}
