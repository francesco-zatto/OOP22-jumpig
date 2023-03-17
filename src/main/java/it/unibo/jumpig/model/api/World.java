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
    Integer getGravity();

    /**
     * Getter for platforms.
     * @return a set of platforms
     */
    Set<Platform> getPlatform();
}
