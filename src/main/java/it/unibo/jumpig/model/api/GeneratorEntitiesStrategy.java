package it.unibo.jumpig.model.api;

import java.util.Set;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.gameentity.GameEntity;

/**
 * The strategy interface to generate entities.
 */

public interface GeneratorEntitiesStrategy {

    /**
     * The refactored algorithm with the strategy pattern.
     * This is the method to generate the various set of entities. 
     * @return the generated set of game entity
     */
    <H extends Hitbox, G extends GameEntity<H>> Set<G> generate(final double maxWidth, final double maxHeight, final Camera camera, final Set<Position> setentities);

    /**
     * The method to generate coordinates (for a generic entity) without generating entities on the same y.
     * In this way I will not generate colliding entities.
     * @param startEntity the coordinates of the entity I'm going to generate.
     * @param maxWidth the width of the game
     * @param maxHeight the height of the game
     * @param setentities the set of each entity's position
     * @param camera the camera of the game
     * @return  the coordinates I'm going to generate
     */
    default Position checkEqualsPosition(final Position startEntity, final double maxWidth, final double maxHeight, final Set<Position> setentities, final Camera camera) {
        return setentities.stream()
            .anyMatch(x -> x.getY() == startEntity.getY()) 
                ? this.checkEqualsPosition(new PositionImpl(Math.random() * maxWidth, 
                        Math.random() * maxHeight * 3 + camera.getCameraHeight()), maxWidth, maxHeight, setentities, camera) 
                : startEntity;
    }

}
