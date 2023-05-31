package it.unibo.jumpig.model.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.Camera;
import it.unibo.jumpig.model.api.GeneratorEntitiesStrategy;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.impl.gameentity.EnemyImpl;

/**
 * The concrete strategy to generate enemies.
 */

public class GenerateEnemiesStrategy implements GeneratorEntitiesStrategy {

    private static final int NUM_ENEMY = 2;    /* The number of enemies */
    private final Set<Enemy> setenemies = new HashSet<>();

    /**
     *{@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <H extends Hitbox, G extends GameEntity<H>> Set<G> generate(
        final double maxWidth, 
        final double maxHeight, 
        final Camera camera, 
        final Set<Position> setentities
        ) {
            return (Set<G>) this.generateEnemies(maxWidth, maxHeight, camera, setentities);
    }

    /**
     * The method to generate enemies.
     * @param maxWidth the width of the game
     * @param maxHeight the height of the game
     * @param camera the camera of thegame
     * @param setentities the set of entity's positions
     * @return a set of generated enemies.
     */
    private Set<Enemy> generateEnemies(
        final double maxWidth, 
        final double maxHeight, 
        final Camera camera, 
        final Set<Position> setentities
        ) {
            Stream.iterate(0, i -> i + 1)
                    .limit(NUM_ENEMY)
                    .forEach(i -> {
                        final Position coordinate = new PositionImpl(
                            Math.random() * maxWidth, 
                            Math.random() * 2 * maxHeight + camera.getCameraStartHeight());
                        this.setenemies.add(new EnemyImpl(
                            checkEqualsPosition(coordinate, maxWidth, maxHeight, setentities, camera)));
                        setentities.add(coordinate);
                    });
            return setenemies.stream()
                .collect(Collectors.toSet());
    }

}
