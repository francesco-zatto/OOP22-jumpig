package it.unibo.jumpig.model.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.GeneratorEntities;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.impl.gameentity.BasicCoin;
import it.unibo.jumpig.model.impl.gameentity.BasicPlatform;
import it.unibo.jumpig.model.impl.gameentity.EnemyImpl;

/**
 * The class to manage the generation of the entities.
 */

 public class GeneratorEntitiesImpl implements GeneratorEntities {

    private final Set<Platform> setplatforms;
    private final Set<Coin> setcoins;
    private final Set<Enemy> setenemies;
    private final Set<Position> setentities;
    private final double maxWidth;    /* The width of the game */
    private final double maxHeight;    /* The height of the game */
    private static final double NUM_PLATFORM = 50;    /* The number of platforms */
    private static final double NUM_ENEMY = 2;    /* The number of enemies */
    private static final double NUM_COIN = 30;    /* The number of coins */

    /**
     * The constructor to create a new generator of entities.
     * @param width the width of the world
     * @param height the height of the world
     */
    public GeneratorEntitiesImpl(final double width, final double height) {
        this.maxWidth = width;
        this.maxHeight = height;
        this.setplatforms = new HashSet<>();
        this.setcoins = new HashSet<>();
        this.setenemies = new HashSet<>();
        this.setentities = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Platform> generatePlatforms() {
        this.addPlatforms();
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

    private void addPlatforms() {
        for (int i = 0; i < NUM_PLATFORM; i++) {
            final Position coordinate = new PositionImpl(Math.random() * this.maxWidth, Math.random() * this.maxHeight * 2);
            this.setplatforms.add(new BasicPlatform(this.checkEqualsPosition(coordinate), 1));
            this.setentities.add(coordinate);
        }
    }

    private void addEnemies() {
        for (int i = 0; i < NUM_ENEMY; i++) {
            final Position coordinate = new PositionImpl(Math.random() * this.maxWidth, Math.random() * this.maxHeight * 2);
            this.setenemies.add(new EnemyImpl(this.checkEqualsPosition(coordinate)));
            this.setentities.add(coordinate);
        }
    }

    private void addCoins() {
        for (int i = 0; i < NUM_COIN; i++) {
            final Position coordinate = new PositionImpl(Math.random() * this.maxWidth, Math.random() * this.maxHeight * 2);
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
                ? this.checkEqualsPosition(new PositionImpl(Math.random() * this.maxWidth, Math.random() * this.maxHeight * 2)) 
                : startEntity;
    }
}
