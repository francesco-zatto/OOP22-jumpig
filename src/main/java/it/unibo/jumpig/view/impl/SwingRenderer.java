package it.unibo.jumpig.view.impl;

import it.unibo.jumpig.common.impl.hitbox.CircleHitbox;
import it.unibo.jumpig.common.impl.hitbox.RectangleHitbox;
import it.unibo.jumpig.model.api.gameentity.GameEntity;
import it.unibo.jumpig.view.api.Renderer;

/**
 * Class the uses Java Swing library to render the gameEntity.
 */
public final class SwingRenderer implements Renderer {

    @Override
    public void renderPlayer(final GameEntity<RectangleHitbox> entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderPlayer'");
    }

    @Override
    public void renderEnemy(final GameEntity<RectangleHitbox> entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderEnemy'");
    }

    @Override
    public void renderBasicPlatform(final GameEntity<RectangleHitbox> entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderBasicPlatform'");
    }

    @Override
    public void renderVanishingPlatform(final GameEntity<RectangleHitbox> entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderVanishingPlatform'");
    }

    @Override
    public void renderCoin(final GameEntity<CircleHitbox> entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderCoin'");
    }
}
