package it.unibo.jumpig.model.api.gameentity;
/**
 * The interface to understand if a targettable entity should remain visible or not.
 * For example, if a coin has been taken it has to disappear.
 */
public interface Targettable {
    boolean isTaken();
}
