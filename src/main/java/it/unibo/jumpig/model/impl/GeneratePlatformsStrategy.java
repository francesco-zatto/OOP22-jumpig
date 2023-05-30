package it.unibo.jumpig.model.impl;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private static final int NUM_BASIC_PLATFORM = 22;    /* The number of basic platforms */
    private static final int NUM_VANISHING_PLATFORM = 8;    /* The number of vanishing platforms */
    private static final int NUM_BROKEN_PLATFORM = 5;    /* The number of broken platforms */
    private static final double VERTICAL_JUMP_VELOCITY = 20;    /* The vertical velocity the player 
                                                                gains when he jumps on a platform */
    private static final int COSTANT_TO_CHECK_GENERATION_DISTANCE = 5;   /* The dividend 
                                                                            to get the minimum distance 
                                                                            between entities */

    /**
    * {@inheritDoc}}
    */
    @Override
    @SuppressWarnings("unchecked")
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
            this.addPlatforms(maxWidth, maxHeight, camera, setentities);
            return setplatforms.stream()
                .collect(Collectors.toSet());
    }

    private void addPlatforms(final double maxWidth, 
        final double maxHeight, 
        final Camera camera, 
        final Set<Position> setentities
        ) {
            Stream.iterate(0, i -> i + 1)
                    .limit(NUM_BROKEN_PLATFORM + NUM_BASIC_PLATFORM + NUM_VANISHING_PLATFORM)
                    .forEach(i -> {
                        final Position coordinate = new PositionImpl(
                            Math.random() * maxWidth, 
                            random.nextDouble(maxHeight * 2) + camera.getCameraStartHeight());
                        if (i < NUM_BROKEN_PLATFORM) {
                            this.setplatforms.add(new BrokenPlatform(
                                this.checkEqualsPosition(
                                    coordinate, 
                                    maxWidth, 
                                    maxHeight, 
                                    setentities, 
                                    camera
                                    )));
                        } else {
                            if (i < NUM_BROKEN_PLATFORM + NUM_BASIC_PLATFORM) {
                                this.setplatforms.add(new BasicPlatform(
                                    checkEqualsPosition(
                                        checkGeneration(
                                            coordinate, 
                                            maxWidth, 
                                            maxHeight, 
                                            setentities, 
                                            camera
                                            ), 
                                        maxWidth, 
                                        maxHeight, 
                                        setentities, 
                                        camera
                                        ), 
                                    VERTICAL_JUMP_VELOCITY));
                            } else {
                                this.setplatforms.add(new VanishingPlatform(
                                    this.checkEqualsPosition(
                                        checkGeneration(
                                            coordinate, 
                                            maxWidth, 
                                            maxHeight, 
                                            setentities, 
                                            camera
                                            ), 
                                        maxWidth, 
                                        maxHeight, 
                                        setentities, 
                                        camera
                                        ), 
                                    VERTICAL_JUMP_VELOCITY));
                            }
                        }
                        setentities.add(coordinate);
                    });
    }

    private Position checkGeneration(
        final Position coordinate, 
        final double maxWidth, 
        final double maxHeight, 
        final Set<Position> setentities, 
        final Camera camera
        ) {
            return coordinate.getY() 
                - setentities.stream()
                        .max((x, y) -> Double.compare(x.getY(), y.getY()))
                        .get()
                        .getY() 
                + 1
                >= maxHeight / COSTANT_TO_CHECK_GENERATION_DISTANCE
                ? this.checkGeneration(new PositionImpl(
                        Math.random() * maxWidth, 
                        Math.random() * 2 * maxHeight + camera.getCameraStartHeight()), 
                    maxWidth, 
                    maxHeight, 
                    setentities, 
                    camera
                    ) 
                : coordinate;
    }

}
