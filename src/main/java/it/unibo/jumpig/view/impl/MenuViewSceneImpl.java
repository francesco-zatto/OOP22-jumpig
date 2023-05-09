package it.unibo.jumpig.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import it.unibo.jumpig.controller.api.MenuController;
import it.unibo.jumpig.view.api.MenuViewScene;

/**
 * Class that displays the game's main menu.
 */
public class MenuViewSceneImpl implements MenuViewScene {

    private static final String FRAME_TITLE = "Jumpig";
    private final JFrame frame = new JFrame(FRAME_TITLE);
    private final MenuController controller;
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension startScreen = new Dimension((int) screen.getWidth() / 5, 
        (int) (screen.getWidth() / 5 * 1.7));
    /**
     * Contructor for building the view.
     * @param controller the controller that manages the interactions in the menu
     */
    public MenuViewSceneImpl(final MenuController controller) {
        /*
         * Controller initialization.
         */
        this.controller = controller;
        /*
         * Once you click the window's close button, the app will close.
         */
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /*
         * Panel for the buttons.
         */
        final JButton gameButton = new JButton("START GAME");
        final JButton leaderboardButton = new JButton("LEADERBOARD");
        final JButton quitButton = new JButton("QUIT");
        /*
         * Set the buttons' dimensions
         */
        final Dimension buttonSize = new Dimension(200, 50);
        gameButton.setPreferredSize(buttonSize);
        leaderboardButton.setPreferredSize(buttonSize);
        quitButton.setPreferredSize(buttonSize);
        /*
         * Creating a new panel that contains the buttons
         */
        final JPanel panelButton = new JPanel(new GridLayout(3, 1, 10, 10));
        panelButton.setBackground(Color.LIGHT_GRAY);
        panelButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelButton.add(gameButton);
        panelButton.add(leaderboardButton);
        panelButton.add(quitButton);
        /*
         * Creating the menu panel that contains panelButton
         */
        final JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(Color.WHITE);
        menuPanel.add(panelButton);
        /*
         * Creating mainPanel that contains menuPanel
         */
        final JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(menuPanel, BorderLayout.CENTER);
        /*
         * Adding mainPanel into the frame.
         */
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        /*
         * Resizing the frame based on the screen dimensions.
         */
        frame.setSize(this.startScreen);
        frame.setLocationByPlatform(true);
        frame.setPreferredSize(frame.getSize());
        frame.setResizable(true);
        /*
         * Adding listeners to buttons.
         */
        quitButton.addActionListener(e -> this.controller.close());
        gameButton.addActionListener(e -> {
            String username = "";
            while ("".equals(username)) {
                username = JOptionPane.showInputDialog(
                        frame,
                        "Enter a valid username",
                        FRAME_TITLE,
                        JOptionPane.PLAIN_MESSAGE
                    );
                if ("".equals(username)) {
                    JOptionPane.showMessageDialog(frame,
                        "ENTER A VALID USERNAME!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                }
            }
            final Runnable thread = controller :: notifyStartGame;
            final Thread runthread = new Thread(thread);
            runthread.start();
        });
        leaderboardButton.addActionListener(e -> this.controller.notifyStartLeaderboard());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void quit() {
        final int option = JOptionPane.showConfirmDialog(frame,
                "Do you really want to quit?",
                "Quitting..", JOptionPane.YES_NO_OPTION); 
        if (option == JOptionPane.YES_OPTION) {
            this.frame.setVisible(false);
            this.frame.dispose();
            Thread.currentThread().interrupt();
        }
    }
    /*
    public static void main(String[] args) {
        new MenuViewSceneImpl(null).show();
    }
    */
}
