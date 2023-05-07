package it.unibo.jumpig.model.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.Camera;
import it.unibo.jumpig.model.api.GeneratorEntitiesStrategy;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.impl.gameentity.BasicPlatform;
import it.unibo.jumpig.model.impl.gameentity.BrokenPlatform;
import it.unibo.jumpig.model.impl.gameentity.VanishingPlatform;

/**
 * The concrete strategy to generate platforms.
 */

public class GeneratePlatformsStrategy<H extends Hitbox, G extends GameEntity<H>> implements GeneratorEntitiesStrategy<H, G> {

    private final double maxWidth=1;    /* The width of the game */
    private final double maxHeight=1;    /* The height of the game */
    private final Set<Platform> setplatforms = new HashSet<>();
    private final Set<Position> setentities = new HashSet<>();
    private final Camera camera = null;
    private final Random random = new Random();;    /* used to create platforms that have the ordinate 
                                        uniformly distributed in their ranges */
    private static final int NUM_BASIC_PLATFORM = 21;    /* The number of basic platforms */
    private static final int NUM_VANISHING_PLATFORM = 4;    /* The number of vanishing platforms */
    private static final int NUM_BROKEN_PLATFORM = 5;    /* The number of broken platforms */
    private static final double VERTICAL_JUMP_VELOCITY = 12;    /* The vertical velocity the player 
                                                                gains when he jumps on a platform */

    @Override
    public Set<G> generate() {
        return (Set<G>) this.generatePlatforms();
    }
    
    /**
     * The method to generate platforms.
     * @return a set of generated platforms.
     */
    private Set<Platform> generatePlatforms(){
        this.addBasicPlatforms();
        this.addVanishingPlatforms();
        this.addBrokenPlatforms();
        return setplatforms.stream()
            .collect(Collectors.toSet());
    }

    private void addBasicPlatforms() {
        for (int i = 0; i < NUM_BASIC_PLATFORM; i++) {
            final Position coordinate = new PositionImpl(
                    Math.random() * this.maxWidth, 
                    random.nextDouble() * this.maxHeight * 3 + this.camera.getCameraHeight());
            this.setplatforms.add(new BasicPlatform(
                    checkEqualsPosition(coordinate, maxWidth, maxHeight, setentities, camera), VERTICAL_JUMP_VELOCITY));
            this.setentities.add(coordinate);
        }
    }

    private void addVanishingPlatforms() {
        for (int i = 0; i < NUM_VANISHING_PLATFORM; i++) {
            final Position coordinate = new PositionImpl(
                    Math.random() * this.maxWidth, 
                    random.nextDouble() * this.maxHeight * 3 + this.camera.getCameraHeight());
            this.setplatforms.add(new VanishingPlatform(
                    this.checkEqualsPosition(coordinate, maxWidth, maxHeight, setentities, camera), VERTICAL_JUMP_VELOCITY));
            this.setentities.add(coordinate);
        }
    }

    private void addBrokenPlatforms() {
        for (int i = 0; i < NUM_BROKEN_PLATFORM; i++) {
            final Position coordinate = new PositionImpl(
                    Math.random() * this.maxWidth, 
                    Math.random() * this.maxHeight * 3 + this.camera.getCameraHeight());
            this.setplatforms.add(new BrokenPlatform(
                    this.checkEqualsPosition(coordinate, maxWidth, maxHeight, setentities, camera)));
            this.setentities.add(coordinate);
        }
    }

}
