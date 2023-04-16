package it.unibo.jumpig.common.impl.hitbox;

import it.unibo.jumpig.common.api.Position;

/**
 * The class that represent an enemy's hitbox.
 */

public class EnemyHitbox extends RectangleHitbox {

    private static final double ENEMY_WIDTH = 8;
    private static final double ENEMY_HEIGHT = 8;

    /**
     * Constructor for the enemy's hitbox.
     * @param center the center of the hitbox.
     */
    public EnemyHitbox(final Position center) {
        super(center, ENEMY_WIDTH, ENEMY_HEIGHT);
    }
}
