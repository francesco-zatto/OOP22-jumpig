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

public class GenerateCoinsStrategy<H extends Hitbox, G extends GameEntity<H>> implements GeneratorEntitiesStrategy<H, G> {

    private static final int NUM_COIN = 6;    /* The number of coins */
    private final double maxWidth=1;    /* The width of the game */
    private final double maxHeight=1;    /* The height of the game */
    private final Set<Coin> setcoins = new HashSet<>();
    private final Set<Position> setentities = new HashSet<>();
    private final Camera camera = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<G> generate() {
        return (Set<G>) this.generateCoins();
    }
    
    /**
     * The method to generate coins.
     * @return a set of generated coins.
     */
    private Set<Coin> generateCoins(){
        this.addCoins();
        return setcoins.stream()
            .collect(Collectors.toSet());
    }

    private void addCoins() {
        for (int i = 0; i < NUM_COIN; i++) {
            final Position coordinate = new PositionImpl(
                    Math.random() * this.maxWidth, 
                    Math.random() * this.maxHeight * 3 + this.camera.getCameraHeight());
            this.setcoins.add(new BasicCoin(checkEqualsPosition(coordinate, maxWidth, maxHeight, setentities, camera)));
            this.setentities.add(coordinate);
        }
    }

}
