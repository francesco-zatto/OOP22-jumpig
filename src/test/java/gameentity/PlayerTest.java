package gameentity;

import it.unibo.jumpig.common.impl.PositionImpl;
import it.unibo.jumpig.model.api.gameentity.Player;
import it.unibo.jumpig.model.impl.gameentity.PlayerImpl;

/**
 * Class to test the correctness of PlayerImpl.
 * {@link it.unibo.jumpig.model.impl.gameentity.PlayerImpl}
 */
public class PlayerTest {

    private final Player player = new PlayerImpl(new PositionImpl(0, 0));
    
}
