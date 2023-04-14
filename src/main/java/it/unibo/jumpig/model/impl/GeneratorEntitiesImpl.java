package it.unibo.jumpig.model.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.GeneratorEntities;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.impl.gameentity.BasicCoin;
import it.unibo.jumpig.model.impl.gameentity.BasicPlatform;

/**
 * The class to manage the generation of the entities.
 */

public class GeneratorEntitiesImpl implements GeneratorEntities {

    private final Set<Platform> setplatforms;
    private final Set<Coin> setcoins;
    private static final double MAX_WIDTH = 36;    /* The width of the game */
    private static final double MAX_HEIGHT = 64;    /* The height of the game */
    private static final double NUM_PLATFORM = 50;    /* The number of platforms */
    private static final double NUM_COIN = 30;    /* The number of coins */

    /**
     * The constructor to create a new generator of entities.
     */
    public GeneratorEntitiesImpl() {
        this.setplatforms = new HashSet<>();
        this.setcoins = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Platform> generatePlatforms() {
        this.addPlatforms();
        return setplatforms.stream().collect(Collectors.toSet());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Enemy> generateEnemies() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateEnemies'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Coin> generateCoins() {
        this.addCoins();
        return setcoins.stream().collect(Collectors.toSet());
    }

    private void addPlatforms() {
        for (int i = 0; i < NUM_PLATFORM; i++) {
            final Position coordinate = new PositionImpl(Math.random() * MAX_WIDTH, Math.random() * MAX_HEIGHT * 2);
            this.setplatforms.add(new BasicPlatform(this.checkEqualsPosition(coordinate), 1));
        }
    }

    private void addCoins() {
        for (int i = 0; i < NUM_COIN; i++) {
            final Position coordinate = new PositionImpl(Math.random() * MAX_WIDTH, Math.random() * MAX_HEIGHT * 2);
            this.setcoins.add(new BasicCoin(this.checkEqualsPosition(coordinate)));
        }
    }

    /**
     * The method to generate coordinates (for a generic entity) without generating entities on the same y.
     * In this way I will not generate colliding entities.
     * @param startEntity the coordinates of the entity I'm going to generate.
     * @return  the coordinates I'm going to generate.
     */
   private Position checkEqualsPosition(final Position startEntity) {
        return Stream.concat(this.setplatforms.stream(), this.setcoins.stream()).anyMatch(
            x -> x.getPosition().getY() == startEntity.getY()
        ) ? this.checkEqualsPosition(
            new PositionImpl(Math.random() * MAX_WIDTH, Math.random() * MAX_HEIGHT * 2)) : startEntity;
    }
}
