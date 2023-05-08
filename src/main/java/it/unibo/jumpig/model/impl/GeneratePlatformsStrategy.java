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

public class GeneratePlatformsStrategy implements GeneratorEntitiesStrategy {

    private final Set<Platform> setplatforms = new HashSet<>();
    private final Random random = new Random();    /* used to create platforms that have the ordinate 
                                        uniformly distributed in their ranges */
    private static final int NUM_BASIC_PLATFORM = 21;    /* The number of basic platforms */
    private static final int NUM_VANISHING_PLATFORM = 4;    /* The number of vanishing platforms */
    private static final int NUM_BROKEN_PLATFORM = 5;    /* The number of broken platforms */
    private static final double VERTICAL_JUMP_VELOCITY = 20;    /* The vertical velocity the player 
                                                                gains when he jumps on a platform */

    /**
    * {@inheritDoc}}
    */
    @Override
    public <H extends Hitbox, G extends GameEntity<H>> Set<G> generate(
        final double maxWidth, 
        final double maxHeight, 
        final Camera camera, 
        final Set<Position> setentities
        ) {
            return (Set<G>) this.generatePlatforms(maxWidth, maxHeight, camera, setentities);
    }

    /**
     * The method to generate platforms.
      * @param maxWidth the width of the game
     * @param maxHeight the height of the game
     * @param camera the camera of thegame
     * @param setentities the set of entity's positions
     * @return a set of generated platforms.
     */
    private Set<Platform> generatePlatforms(
        final double maxWidth, 
        final double maxHeight, 
        final Camera camera, 
        final Set<Position> setentities
        ) {
            this.addBasicPlatforms(maxWidth, maxHeight, camera, setentities);
            this.addVanishingPlatforms(maxWidth, maxHeight, camera, setentities);
            this.addBrokenPlatforms(maxWidth, maxHeight, camera, setentities);
            return setplatforms.stream()
                .collect(Collectors.toSet());
    }

    private void addBasicPlatforms(
        final double maxWidth, 
        final double maxHeight, 
        final Camera camera, 
        final Set<Position> setentities
        ) {
            for (int i = 0; i < NUM_BASIC_PLATFORM; i++) {
                final Position coordinate = new PositionImpl(
                        Math.random() * maxWidth, 
                        random.nextDouble() * maxHeight * 2 + camera.getCameraHeight());
                this.setplatforms.add(new BasicPlatform(
                        checkEqualsPosition(coordinate, maxWidth, maxHeight, setentities, camera), 
                        VERTICAL_JUMP_VELOCITY));
                setentities.add(coordinate);
            }
    }

    private void addVanishingPlatforms(
        final double maxWidth, 
        final double maxHeight, 
        final Camera camera, 
        final Set<Position> setentities
        ) {
            for (int i = 0; i < NUM_VANISHING_PLATFORM; i++) {
                final Position coordinate = new PositionImpl(
                        Math.random() * maxWidth, 
                        random.nextDouble() * maxHeight * 2 + camera.getCameraHeight());
                this.setplatforms.add(new VanishingPlatform(
                        this.checkEqualsPosition(coordinate, maxWidth, maxHeight, setentities, camera), 
                        VERTICAL_JUMP_VELOCITY));
                setentities.add(coordinate);
            }
    }

    private void addBrokenPlatforms(
        final double maxWidth, 
        final double maxHeight, 
        final Camera camera, 
        final Set<Position> setentities
        ) {
            for (int i = 0; i < NUM_BROKEN_PLATFORM; i++) {
                final Position coordinate = new PositionImpl(
                        Math.random() * maxWidth, 
                        Math.random() * maxHeight * 2 + camera.getCameraHeight());
                this.setplatforms.add(new BrokenPlatform(
                        this.checkEqualsPosition(coordinate, maxWidth, maxHeight, setentities, camera)));
                setentities.add(coordinate);
            }
    }

}
