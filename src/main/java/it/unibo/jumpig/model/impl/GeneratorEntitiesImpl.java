package it.unibo.jumpig.model.impl;

import java.util.HashSet;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.Camera;
import it.unibo.jumpig.model.api.GeneratorEntitiesStrategy;
import it.unibo.jumpig.model.api.gameentity.GameEntity;

/**
 * The class to manage the generation of the entities.
 */

 public class GeneratorEntitiesImpl {

    private GeneratorEntitiesStrategy generateStrategy;
    private final Set<Position> setentities;
    private final Camera camera;
    private final double maxWidth;    /* The width of the game */
    private final double maxHeight;    /* The height of the game */

    /**
     * The constructor to create a new generator of entities.
     * @param width the width of the world
     * @param height the height of the world
     * @param camera the camera of the world
     */
    @SuppressFBWarnings(value = "EI2", 
        justification = "Necessary to have the cameraheight (from the camera) that change during the game")

    public GeneratorEntitiesImpl(
        final double width, 
        final double height, 
        final Camera camera
        ) {
            this.maxWidth = width;
            this.maxHeight = height;
            this.setentities = new HashSet<>();
            this.camera = camera;
            this.generateStrategy = new GenerateCoinsStrategy();
    }

    /**
     * The method that calls the method to generate entities of the game.
     * {@link it.unibo.jumpig.model.api.GeneratorEntitiesStrategy}
     * @param <H> the hitbox type
     * @param <G> the game entity's type
     * @return a set of generated entities
     */
    public <H extends Hitbox, G extends GameEntity<H>> Set<G> generateEntities() {
        return generateStrategy.generate(this.maxWidth, this.maxHeight, this.camera, this.setentities);
    }

    /**
     * The method to set the strategy of generation.
     * @param generateStrategyImpl the strategy to set
     */
    public void setGenerateStrategy(final GeneratorEntitiesStrategy generateStrategyImpl) {
        this.generateStrategy = generateStrategyImpl;
    }

}
