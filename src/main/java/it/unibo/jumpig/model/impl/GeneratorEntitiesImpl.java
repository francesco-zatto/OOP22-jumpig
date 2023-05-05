package it.unibo.jumpig.model.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.Camera;
import it.unibo.jumpig.model.api.GeneratorEntities;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.impl.gameentity.BasicCoin;
import it.unibo.jumpig.model.impl.gameentity.BasicPlatform;
import it.unibo.jumpig.model.impl.gameentity.BrokenPlatform;
import it.unibo.jumpig.model.impl.gameentity.EnemyImpl;
import it.unibo.jumpig.model.impl.gameentity.VanishingPlatform;

/**
 * The class to manage the generation of the entities.
 */

 public class GeneratorEntitiesImpl implements GeneratorEntities {

    private final Set<Platform> setplatforms;
    private final Set<Coin> setcoins;
    private final Set<Enemy> setenemies;
    private final Set<Position> setentities;
    private final Camera camera;
    private final double maxWidth;    /* The width of the game */
    private final double maxHeight;    /* The height of the game */
    private static final int NUM_BASIC_PLATFORM = 8;    /* The number of basic platforms */
    private static final int NUM_VANISHING_PLATFORM = 4;    /* The number of vanishing platforms */
    private static final int NUM_ENEMY = 2;    /* The number of enemies */
    private static final int NUM_COIN = 5;    /* The number of coins */
    private static final int NUM_BROKEN_PLATFORM = 2;    /* The number of broken platforms */
    private static final double VERTICAL_JUMP_VELOCITY = 20;    /* The vertical velocity the player 
                                                                gains when he jumps on a platform */

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
            this.setplatforms = new HashSet<>();
            this.setcoins = new HashSet<>();
            this.setenemies = new HashSet<>();
            this.setentities = new HashSet<>();
            this.camera = camera;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Platform> generatePlatforms() {
        this.addBasicPlatforms();
        this.addVanishingPlatforms();
        this.addBrokenPlatforms();
        return setplatforms.stream()
            .collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Enemy> generateEnemies() {
        this.addEnemies();
        return setenemies.stream()
            .collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Coin> generateCoins() {
        this.addCoins();
        return setcoins.stream()
            .collect(Collectors.toSet());
    }

    private void addBasicPlatforms() {
        for (int i = 0; i < NUM_BASIC_PLATFORM; i++) {
            final Position coordinate = new PositionImpl(
                    Math.random() * this.maxWidth, 
                    Math.random() * this.maxHeight * 3 + this.camera.getCameraHeight());
            this.setplatforms.add(new BasicPlatform(
                    this.checkEqualsPosition(coordinate), VERTICAL_JUMP_VELOCITY));
            this.setentities.add(coordinate);
        }
    }

    private void addVanishingPlatforms() {
        for (int i = 0; i < NUM_VANISHING_PLATFORM; i++) {
            final Position coordinate = new PositionImpl(
                    Math.random() * this.maxWidth, 
                    Math.random() * this.maxHeight * 3 + this.camera.getCameraHeight());
            this.setplatforms.add(new VanishingPlatform(
                    this.checkEqualsPosition(coordinate), VERTICAL_JUMP_VELOCITY));
            this.setentities.add(coordinate);
        }
    }

    private void addBrokenPlatforms() {
        for (int i = 0; i < NUM_BROKEN_PLATFORM; i++) {
            final Position coordinate = new PositionImpl(
                    Math.random() * this.maxWidth, 
                    Math.random() * this.maxHeight * 3 + this.camera.getCameraHeight());
            this.setplatforms.add(new BrokenPlatform(
                    this.checkEqualsPosition(coordinate)));
            this.setentities.add(coordinate);
        }
    }

    private void addEnemies() {
        for (int i = 0; i < NUM_ENEMY; i++) {
            final Position coordinate = new PositionImpl(
                    Math.random() * this.maxWidth, 
                    Math.random() * this.maxHeight * 3 + this.camera.getCameraHeight());
            this.setenemies.add(new EnemyImpl(this.checkEqualsPosition(coordinate)));
            this.setentities.add(coordinate);
        }
    }

    private void addCoins() {
        for (int i = 0; i < NUM_COIN; i++) {
            final Position coordinate = new PositionImpl(
                    Math.random() * this.maxWidth, 
                    Math.random() * this.maxHeight * 3 + this.camera.getCameraHeight());
            this.setcoins.add(new BasicCoin(this.checkEqualsPosition(coordinate)));
            this.setentities.add(coordinate);
        }
    }

    /**
     * The method to generate coordinates (for a generic entity) without generating entities on the same y.
     * In this way I will not generate colliding entities.
     * @param startEntity the coordinates of the entity I'm going to generate.
     * @return  the coordinates I'm going to generate.
     */
   private Position checkEqualsPosition(final Position startEntity) {
        return this.setentities.stream()
            .anyMatch(x -> x.getY() == startEntity.getY()) 
                ? this.checkEqualsPosition(new PositionImpl(Math.random() * this.maxWidth, 
                        Math.random() * this.maxHeight * 3 + this.camera.getCameraHeight())) 
                : startEntity;
    }
}
