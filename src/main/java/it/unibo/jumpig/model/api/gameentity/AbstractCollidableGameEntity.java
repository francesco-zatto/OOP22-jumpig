package it.unibo.jumpig.model.api.gameentity;

import it.unibo.jumpig.common.api.Position;
import it.unibo.jumpig.common.api.hitbox.Hitbox;

public abstract class AbstractCollidableGameEntity<H extends Hitbox> extends AbstractGameEntity<H> implements Collidable {

    public AbstractCollidableGameEntity(Position position, H hitbox) {
        super(position, hitbox);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void handleCollision(Player player) {
        // TODO Auto-generated method stub
        
    }
    
}
