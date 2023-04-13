package it.unibo.jumpig.model.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.GeneratorEntities;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.impl.gameentity.BasicPlatform;

/**
 * The class to manage the generation of the entities.
 */

public class GeneratorEntitiesImpl implements GeneratorEntities {

    private static final double MAX_WIDTH = 36;    /* The width of the game */
    private static final double MAX_HEIGHT = 64;    /* The height of the game */
    private static final double NUM_PLATFORM = 50;    /* The number of platforms */

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Platform> generatePlatforms() {
        final Set<Platform> setplatforms = new HashSet<>();
        this.addPlatforms(setplatforms);
        return setplatforms;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCoins'");
    }

    private void addPlatforms(final Set<Platform> setplatforms) {
        for (int i = 0; i < NUM_PLATFORM; i++) {
            final Position coordinate = new PositionImpl(Math.random() * MAX_WIDTH, Math.random() * MAX_HEIGHT * 2);
            setplatforms.add(new BasicPlatform(this.checkEqualsPosition(setplatforms, coordinate), 1));
        }
    }

    /**
     * The method to generate coordinates for a generic entity X that have not been used yet.
     * In this way I will not generate colliding entities.
     * @param <X> a generic entity
     * @param <H> a generic Hitbox
     * @param setentities a generic set to use this method with all other methods.
     * @param startEntity the coordinates of the entity I'm going to generate.
     * @return  the coordinates I'm going to generate.
     */
   private <X extends GameEntity<H>, H extends Hitbox> Position checkEqualsPosition(
    final Set<X> setentities, final Position startEntity) {
        return setentities.stream().anyMatch(x -> x.getPosition().getX() == startEntity.getX() 
        && x.getPosition().getY() == startEntity.getY()
        ) ? this.checkEqualsPosition(setentities, 
        new PositionImpl(Math.random() * MAX_WIDTH, Math.random() * MAX_HEIGHT * 2)) : startEntity;
    }
}
