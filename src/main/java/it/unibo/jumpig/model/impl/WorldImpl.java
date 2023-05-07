package it.unibo.jumpig.model.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.jumpig.common.api.hitbox.Hitbox;
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
import it.unibo.jumpig.model.impl.gameentity.VanishingPlatform;

/**
 * The class to manage the world of the game.
 */

public class WorldImpl implements World {

    private static final double WIDTH = 36;
    private static final double HEIGHT = 64;
    private static final double GRAVITY = -3;
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
        this.camera = new CameraImpl(0);
        this.generator = new GeneratorEntitiesImpl(WIDTH, HEIGHT, this.camera);
        this.player = new PlayerImpl(new PositionImpl(WIDTH / 2, 1));
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
        this.setentities.addAll(this.getPlatform());
        this.setentities.add(this.getPlayer());
        return this.setentities.stream()
            .map(x -> this.updateheight(x))
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
        return this.camera.copy();
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
    public void updateGame(final long elapsed, final int direction) {
        final double time = ((double) elapsed) / 1000.0;
        this.player.computeVelocity(GRAVITY, time, direction);
        this.player.computePosition(time);
        this.checkRegeneration();
        final var collidables = this.getCollidables(Set.of(this.setcoins, this.setenemies, this.setplatform));
        collidables.forEach(c -> c.handleCollision(this.player));
        this.setEmpty();
    }

    private void setEmpty() {
        if (this.camera.getPlatformHeight(this.player).isPresent() 
            && this.player.getPosition().getY() < this.camera.getPlatformHeight(this.player).get()) { 
                //-camera.getCameraHeight() ???
                this.player.setLastPlatformHeight(Optional.empty());
        }
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

    private Hitbox updateheight(final GameEntity<? extends Hitbox> x) {
        x.getHitbox()
            .updateHitBox(new PositionImpl(
                    x.getPosition().getX(), 
                    x.getPosition().getY() - this.camera.getCameraHeight()));
        return x.getHitbox();
    }

    private void checkRegeneration() {
        if ((this.player.getPosition().getY() % HEIGHT) < 1
                //check that it's almoast zero meaning that the generator has to regenerate entities.
            ) {
                    this.camera.setCameraHeight((int) this.player.getPosition().getY());
                    setentities.clear();
                    this.setentities.addAll(regenerate());
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
