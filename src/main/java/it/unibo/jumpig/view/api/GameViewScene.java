package it.unibo.jumpig.view.api;

import java.util.Set;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.common.impl.Direction;

/**
 * The interface to show the GameViewScene.
 */

public interface GameViewScene extends ViewScene {

    /**
     * The method to render the entities.
     * @param entities set of the hitboxes of the gameEntities.
     */
    void renderEntities(Set<Hitbox> entities);

    /**
     * The method to render the current score.
     * @param coins number of coins picked by player
     * @param height height reached by the player
     * @param lives lives of the player
     */
    void renderCurrentScore(int coins, int height, int lives);

    /**
     * The method to manage the input.
     * @return 0 for left movement and 1 for right movement
     */
    Direction manageInput();

    /**
     * Method that checks if the view is still active.
     * @return if the view is still active
     */
    boolean isViewActive();
}
