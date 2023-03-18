package it.unibo.jumpig.model.api;

import java.util.Set;

/**
 * Interface World.
 */
public interface World {
    /**
     * Getter for game gravity.
     * @return the value of the gravity
     */
    double getGravity();

    /**
     * Getter for platforms.
     * @return a set of platforms
     */
    Set<Platform> getPlatform();

    /**
     * Getter for the player.
     * @return the player
     */
    Player getPlayer();

    /**
     * Getter for the enemies.
     * @return a set of enemies
     */
    Set<Enemy> getEnemies();

    /**
     * Getter for the coins.
     * @return a set of coins
     */
    Set<Coin> getCoins();
}