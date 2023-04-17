package it.unibo.jumpig.model.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.model.api.Camera;
import it.unibo.jumpig.model.api.GeneratorEntities;
import it.unibo.jumpig.model.api.World;
import it.unibo.jumpig.model.api.gameentity.Coin;
import it.unibo.jumpig.model.api.gameentity.Collidable;
import it.unibo.jumpig.model.api.gameentity.CollidableEntity;
import it.unibo.jumpig.model.api.gameentity.Enemy;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.model.api.gameentity.Platform;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.api.gameentity.Targettable;
import it.unibo.jumpig.model.impl.gameentity.PlayerImpl;
import it.unibo.jumpig.model.impl.gameentity.VanishingPlatform;

/**
 * The class to manage the world of the game.
 */

public class WorldImpl implements World {

    private static final double WIDTH = 36; //NOPMD
    private static final double HEIGHT = 64;
    private static final double GRAVITY = 9.8;
    private final GeneratorEntities generator; //NOPMD
    private final Player player;
    private final Set<Platform> setplatform;
    private final Set<Enemy> setenemies;
    private final Set<Coin> setcoins;
    private final Camera camera;
    private final Set<GameEntity<? extends Hitbox>> setentities;

    /**
     * The constructor to create a new world.
     */

    public WorldImpl() {
        this.generator = new GeneratorEntitiesImpl(WIDTH, HEIGHT);
        this.player = new PlayerImpl(null);
        this.setplatform = generator.generatePlatforms();
        this.setenemies = generator.generateEnemies();
        this.setcoins = generator.generateCoins();
        this.camera = new CameraImpl();
        this.setentities = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getGravity() {
        return GRAVITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return this.player.copy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Hitbox> getEntities() {
        this.setentities.addAll(this.getCoins());
        this.setentities.addAll(this.getEnemies());
        this.setentities.addAll(this.getPlatform());
        return this.setentities.stream()
            .map(x -> x.getHitbox())
            .collect(Collectors.toSet());
    }

    /**
     * The method to filter only visible platforms.
     * @return the set of platforms.
     */
    private Set<Platform> getPlatform() {
        return this.setplatform.stream()
            .filter(x -> x.getClass() == VanishingPlatform.class && !((Targettable) x).isTaken() 
                || x.getClass() != VanishingPlatform.class)
            .collect(Collectors.toSet());
    }

    /**
     * The method to filter only visible enemies.
     * @return the set of enemies.
     */
    private Set<Enemy> getEnemies() {
        return this.setenemies.stream()
            .filter(x -> !x.isTaken())
            .collect(Collectors.toSet());
    }

    /**
     * The method to filter only visible coins.
     * @return the set of coins.
     */
    private Set<Coin> getCoins() {
       return this.setcoins.stream()
            .filter(x -> !x.isTaken())
            .collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Camera getCamera() {
        return this.camera;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return HEIGHT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return WIDTH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGame(final long elapsed) {
        final var collidables = this.getCollidables(Set.of(this.setcoins, this.setenemies, this.setplatform));
        collidables.forEach(c -> c.handleCollision(this.player));
    }

    private Set<Collidable> getCollidables(final Set<Set<? extends CollidableEntity<? extends Hitbox>>> collidableSets) {
        return collidableSets.stream()
                .flatMap(Set::stream)
                .filter(this::isEntityNearPlayer)
                .collect(Collectors.toSet());
    }

    private boolean isEntityNearPlayer(final GameEntity<? extends Hitbox> entity) {
        final double quarterOfWorld = HEIGHT / 4;
        final double playerHeight = this.player.getPosition().getY();
        final double entityHeight = entity.getPosition().getY();
        return playerHeight - quarterOfWorld < entityHeight && entityHeight < playerHeight + quarterOfWorld;
    }

    //we will use this in updateGame
    private Set<GameEntity<? extends Hitbox>> regenerate(final Set<GameEntity<? extends Hitbox>> setToRegenerate) { //NOPMD
        final var type = setToRegenerate.stream()
            .toList()
            .get(0);
        setToRegenerate.clear();
        if (type instanceof Platform) {
            setToRegenerate.addAll(this.generator.generatePlatforms());
        }
        if (type instanceof Coin) {
            setToRegenerate.addAll(this.generator.generateCoins());
        }
        if (type instanceof Enemy) {
            setToRegenerate.addAll(this.generator.generateEnemies());
        }
        return setToRegenerate;
    }
}
