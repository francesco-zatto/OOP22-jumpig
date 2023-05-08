package it.unibo.jumpig.model.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.Camera;
import it.unibo.jumpig.model.api.GeneratorEntitiesStrategy;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.impl.gameentity.BasicCoin;

/**
 * The concrete strategy to generate coins.
 */

public class GenerateCoinsStrategy implements GeneratorEntitiesStrategy {

    private static final int NUM_COIN = 6;    /* The number of coins */
    private final Set<Coin> setcoins = new HashSet<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public <H extends Hitbox, G extends GameEntity<H>> Set<G> generate(
            final double maxWidth,
            final double maxHeight,
            final Camera camera,
            final Set<Position> setentities
            ) {
                return (Set<G>) this.generateCoins(maxWidth, maxHeight, camera, setentities);
    }

    /**
     * The method to generate coins.
     * @param maxWidth the width of the game
     * @param maxHeight the height of the game
     * @param camera the camera of thegame
     * @param setentities the set of entity's positions
     * @return a set of generated coins.
     */
    private Set<Coin> generateCoins(
        final double maxWidth, 
        final double maxHeight, 
        final Camera camera, 
        final Set<Position> setentities
        ) {
            for (int i = 0; i < NUM_COIN; i++) {
                final Position coordinate = new PositionImpl(
                        Math.random() * maxWidth, 
                        Math.random() * maxHeight * 3 + camera.getCameraHeight());
                this.setcoins.add(new BasicCoin(
                        checkEqualsPosition(coordinate, maxWidth, maxHeight, setentities, camera)));
                setentities.add(coordinate);
            }
            return setcoins.stream()
                .collect(Collectors.toSet());
    }

}
