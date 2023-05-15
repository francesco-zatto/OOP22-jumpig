package it.unibo.jumpig.model.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
import it.unibo.jumpig.common.impl.Direction;
import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.Camera;
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

/**
 * The class to manage the world of the game.
 */

public class WorldImpl implements World {

    private static final double WIDTH = 36;
    private static final double HEIGHT = 64;
    private static final double GRAVITY = -9;
    private GeneratorEntitiesImpl generator;
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
        this.player = new PlayerImpl(new PositionImpl(WIDTH / 2, 1));
        this.camera = new CameraImpl(this.player);
        this.generator = new GeneratorEntitiesImpl(WIDTH, HEIGHT, this.camera);
        generator.setGenerateStrategy(new GeneratePlatformsStrategy());
        this.setplatform = generator.generateEntities();
        generator.setGenerateStrategy(new GenerateEnemiesStrategy());
        this.setenemies = generator.generateEntities();
        generator.setGenerateStrategy(new GenerateCoinsStrategy());
        this.setcoins = generator.generateEntities();
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
        this.setentities.clear();
        this.setentities.addAll(this.getCoins());
        this.setentities.addAll(this.getEnemies());
        this.setentities.addAll(this.getPlatforms());
        this.setentities.add(this.getPlayer());
        return this.setentities.stream()
                .map(x -> x.createScaledHitbox(
                        new PositionImpl(
                            x.getPosition().getX(), 
                            x.getPosition().getY() - camera.getCameraHeight())))
                .collect(Collectors.toSet());
    }

    /**
     * The method to get only visible platforms.
     * @return the set of platforms.
     */
    private Set<Platform> getPlatforms() {
        final Set<Platform> takenPlatforms = this.setplatform.stream()
            .filter(x -> x instanceof Targettable && ((Targettable) x).isTaken())
            .collect(Collectors.toSet());
        return this.setplatform.stream().filter(x -> !takenPlatforms.contains(x))
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
        return this.camera.copy(this.player);
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
    public void updateGame(final long elapsed, final Direction direction) {
        final double time = ((double) elapsed) / 1000.0;
        if (this.player.getPosition().getX() > getWidth()) {
            this.player.moveToEdges(new PositionImpl(0, this.player.getPosition().getY()));
        } else if (this.player.getPosition().getX() < 0) {
            this.player.moveToEdges(new PositionImpl(WIDTH, this.player.getPosition().getY()));
        }
        this.player.computeVelocity(GRAVITY, time, direction);
        this.player.computePosition(time);
        updateCollisions();
        this.checkRegeneration();
        this.setEmpty();
        this.camera.setCameraVelocity(this.player);
        this.computeCameraHeight(time);
        this.camera.setLastPlatformHeight(this.player.getLastPlatformHeight());
    }

    private void updateCollisions() {
        final var collidableEntities = this.getCollidableEntities(Set.of(this.setcoins, this.setenemies, this.setplatform));
        collidableEntities.forEach(c -> c.handleCollision(this.player));
    }

    private void computeCameraHeight(final double time) {
        if (this.player.getPosition().getY() >= (HEIGHT / 2 + this.camera.getCameraHeight())) {
            this.camera.setCameraHeight(time, this.player);
        }
    }

    private void setEmpty() {
        if (this.camera.getPlatformHeight(this.player).isPresent() 
            && this.player.getPosition().getY() < this.camera.getPlatformHeight(this.player).get()) {
                this.player.setLastPlatformHeight(Optional.empty());
        }
    }

    private Set<Collidable> getCollidableEntities(final Set<Set<? extends CollidableEntity<? extends Hitbox>>> collidableSets) {
        return collidableSets.stream()
                .flatMap(Set::stream)
                .filter(this::isAboveCamera)
                .filter(this::isEntityNearPlayer)
                .collect(Collectors.toSet());
    }

    private boolean isEntityNearPlayer(final GameEntity<? extends Hitbox> entity) {
        final double quarterOfWorld = HEIGHT / 4;
        final double playerHeight = this.player.getPosition().getY();
        final double entityHeight = entity.getPosition().getY();
        return playerHeight - quarterOfWorld < entityHeight && entityHeight < playerHeight + quarterOfWorld;
    }

    private boolean isAboveCamera(final GameEntity<? extends Hitbox> entity) {
        return entity.getPosition().getY() > this.camera.getCameraHeight();
    }

    private void checkRegeneration() {
        if ((this.player.getPosition().getY() % HEIGHT) < 1
                //check that it's almoast zero meaning that the generator has to regenerate entities.
            ) {
                    setentities.clear();
                    if (this.player.getVelocity().getYComponent() > 0) {
                        this.regenerate();
                    }
                }
    }
    private Set<GameEntity<? extends Hitbox>> regenerate() {

        this.setplatform.clear();
        this.setcoins.clear();
        this.setenemies.clear();
        generator = new GeneratorEntitiesImpl(WIDTH, HEIGHT, this.camera);
            /* I have to create a new generator to clean up the set of entities' positions 
                which will be compared with positions that are goin to be created 
                (in the method checkEqualsPosition) */
        generator.setGenerateStrategy(new GeneratePlatformsStrategy());
        this.setplatform.addAll(generator.generateEntities());
        generator.setGenerateStrategy(new GenerateEnemiesStrategy());
        this.setenemies.addAll(generator.generateEntities());
        generator.setGenerateStrategy(new GenerateCoinsStrategy());
        this.setcoins.addAll(generator.generateEntities());
        this.setentities.clear();
        this.setentities.addAll(this.setplatform);
        this.setentities.addAll(this.setenemies);
        this.setentities.addAll(this.setcoins);
        return this.setentities;
    }
}
