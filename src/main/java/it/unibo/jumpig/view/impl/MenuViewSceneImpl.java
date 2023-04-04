package it.unibo.jumpig.view.impl;

import static javax.swing.BoxLayout.Y_AXIS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
    /**
     * Contructor for building the view.
     * @param controller the controller that manages the interactions in the menu
     */
    public MenuViewSceneImpl(final MenuController controller) {
        this.controller = controller;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final JPanel panelButton = new JPanel();
        panelButton.setBackground(Color.CYAN);
        panelButton.setLayout(new BoxLayout(panelButton, Y_AXIS));
        final JButton gameButton = new JButton("START GAME");
        final JButton leaderboardButton = new JButton("LEADERBOARD");
        final JButton quitButton = new JButton("QUIT");
        panelButton.add(gameButton);
        panelButton.add(Box.createRigidArea(new Dimension(0, 10)));
        panelButton.add(leaderboardButton);
        panelButton.add(Box.createRigidArea(new Dimension(0, 10)));
        panelButton.add(quitButton);
        final JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(Color.CYAN);
        menuPanel.add(panelButton);
        frame.getContentPane().add(menuPanel, BorderLayout.CENTER);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int screenHeight = (int) screen.getHeight();
        final double percentageHeight = 0.7;
        final int height = (int) (screenHeight * percentageHeight);
        final int width = (int) (height * 0.5625);
        frame.setSize(width, height);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        quitButton.addActionListener(e -> this.controller.close());
        gameButton.addActionListener(e -> this.controller.notifyStartGame());
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
        }
    }
}
