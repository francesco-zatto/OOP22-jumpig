package it.unibo.jumpig.view.impl;

import static javax.swing.BoxLayout.Y_AXIS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import it.unibo.jumpig.view.api.MenuViewScene;

/**
 * Class that displays the game's main menu.
 */
public class MenuViewSceneImpl implements MenuViewScene {

    private static final String FRAME_TITLE = "Jumpig";
    private final JFrame frame = new JFrame(FRAME_TITLE);
    /**
     * Constructor for the main menu.
     * @throws IOException
     */
    public MenuViewSceneImpl() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final JPanel panelButton = new JPanel();
        panelButton.setBackground(Color.CYAN);
        panelButton.setLayout(new BoxLayout(panelButton, Y_AXIS));
        JButton gameButton = new JButton("START GAME");
        panelButton.add(gameButton);
        panelButton.add(Box.createRigidArea(new Dimension(0,10)));
        JButton leaderboardButton = new JButton("LEADERBOARD");
        panelButton.add(leaderboardButton);
        panelButton.add(Box.createRigidArea(new Dimension(0,10)));
        JButton quitButton = new JButton("QUIT");
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
        quitButton.addActionListener(e -> this.quit());
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
    public void quit() {
        int n = JOptionPane.showConfirmDialog(frame,
                "Do you really want to quit?",
                "Quitting..", JOptionPane.YES_NO_OPTION); 
        if (n == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Main to test the GUI menu.
     * @param args strings
     */
    public static void main(final String[] args) {
        new MenuViewSceneImpl().show();
    }
}
