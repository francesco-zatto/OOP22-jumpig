package it.unibo.jumpig.model.api.gameentity;
/**
 * The interface to understand if a targettable entity has to remain visible or not.
 * For example, if a coin has been taken it has to disappear.
 */
public interface Targettable {

    /**
     * The method to set the entity visibility.
     * When this method is called the entity wil disappear.
     */
    void setTaken();

    /**
     * The method to know if the targettable entity has to disappear.
     * @return true if the entity has to disappear.
     */
    boolean isTaken();

}
