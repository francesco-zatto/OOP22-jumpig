package it.unibo.jumpig.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import it.unibo.jumpig.controller.api.MenuController;
import it.unibo.jumpig.view.api.MenuViewScene;

/**
 * Class that displays the game's main menu.
 */
public class MenuViewSceneImpl implements MenuViewScene {

    private static final String FRAME_TITLE = "Jumpig";
    private static final String WELCOME_TITLE = "WELCOME TO JUMPIG!";
    private static final String GAME = "START GAME";
    private static final String LEADERBOARD = "LEADERBOARD";
    private static final String QUIT = "QUIT";
    private static final double ASPECT_RATIO = 16.0 / 9.0;
    private static final double SCREEN_FRACTION = 5;
    private static final int ROWS = 5;
    private static final int FONT_SIZE = 20;
    private static final int VERTICAL_BORDERS = 20;
    private static final int HORIZONTAL_BORDERS = 50;
    private final JFrame frame = new JFrame(FRAME_TITLE);
    private final JTextField textField = new JTextField();
    private final MenuController controller; 
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
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /*
         * Creating the menu panel that contains everything.
         */
        final JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setLayout(
            new GridLayout(
                ROWS, 
                0, 
                VERTICAL_BORDERS / 2, 
                VERTICAL_BORDERS / 2
                )
            );
        menuPanel.setBorder(
            BorderFactory.createEmptyBorder(
                HORIZONTAL_BORDERS, 
                VERTICAL_BORDERS, 
                HORIZONTAL_BORDERS, 
                VERTICAL_BORDERS
                )
            );
        menuPanel.setBackground(Color.PINK);
        /*
         * Adding menuPanel into frame.
         */
        this.frame.getContentPane().add(menuPanel);
        /*
         * Welcome label.
         */
        final JLabel welcomeLabel = new JLabel(WELCOME_TITLE);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, FONT_SIZE));
        menuPanel.add(welcomeLabel);
        /*
         * Creation of usernamePanel.
         */
        final JPanel usernamePanel = new JPanel(new GridLayout(1, 0));
        usernamePanel.setBorder(BorderFactory.createTitledBorder("Enter a username"));
        usernamePanel.setBackground(Color.PINK);
        usernamePanel.add(this.textField);
        /*
         * Adding usernamePanel into the menuPanel.
         */
        menuPanel.add(usernamePanel);
        /*
         * Creation of the buttons.
         */
        final JButton gameButton = new JButton(GAME);
        final JButton leaderboardButton = new JButton(LEADERBOARD);
        final JButton quitButton = new JButton(QUIT);
        /*
         * Adding the buttons into the menuPanel.
         */
        menuPanel.add(gameButton);
        menuPanel.add(leaderboardButton);
        menuPanel.add(quitButton);
        /*
         * Resizing the frame based on the screen dimensions, and with the 16:9 ratio.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension startScreen = new Dimension(
            (int) (screen.getWidth() / SCREEN_FRACTION), 
            (int) (screen.getWidth() / SCREEN_FRACTION * ASPECT_RATIO)
            );
        this.frame.setSize(startScreen);
        this.frame.setLocationByPlatform(true);
        this.frame.setPreferredSize(frame.getSize());
        this.frame.setResizable(true);
        /*
         * Adding action listeners to buttons.
         */
        quitButton.addActionListener(e -> this.controller.close());
        gameButton.addActionListener(e -> {
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
        this.frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        final int option = JOptionPane.showConfirmDialog(this.frame,
                "Do you really want to quit?",
                "Quitting..", JOptionPane.YES_NO_OPTION); 
        if (option == JOptionPane.YES_OPTION) {
            this.frame.setVisible(false);
            this.frame.dispose();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return this.textField.getText();
    }
}
