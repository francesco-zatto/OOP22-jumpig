package it.unibo.jumpig.common.impl.hitbox;

import java.util.Comparator;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.jumpig.common.api.hitbox.Hitbox;

/**
 * A comparator for hitboxes, used by View to render entities in the screen without flickering.
 */
@SuppressFBWarnings(
    value = "SE",
    justification = "This class is not meant to be serialized"
)
public class HitboxComparator implements Comparator<Hitbox> {

    /**
     * This method compares two hitboxes, placing always the player in the background like the original
     * game. When two hitboxes, both of them are not PlayerHitbox, they are sorted by their class name and their
     * hashcode. In this way sorting every hitbox is possible, assuming that every one has a different hashcode. 
     */
    @Override
    public int compare(final Hitbox arg0, final Hitbox arg1) {
        if (arg0 instanceof PlayerHitbox) {
            return -1;
        }
        if (arg1 instanceof PlayerHitbox) {
            return 1;
        }
        return buildNameGameEntity(arg0).compareTo(buildNameGameEntity(arg1));
    }

    private String buildNameGameEntity(final Hitbox arg) {
        return arg.getClass().getSimpleName() + arg.hashCode();
    }
}
