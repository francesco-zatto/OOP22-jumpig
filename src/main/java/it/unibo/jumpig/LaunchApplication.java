package it.unibo.jumpig;

import it.unibo.jumpig.controller.impl.MenuControllerImpl;

/**
 * Main class of the application.
 */
public final class LaunchApplication {

    private LaunchApplication() {
    }

   /**
    * Main method of the application that launches MenuController.
    * @param args not used
    */
    public static void main(final String[] args) {
        new MenuControllerImpl().start();
    }
}
