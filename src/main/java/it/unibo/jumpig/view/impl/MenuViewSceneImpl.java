package it.unibo.jumpig.view.impl;

import static javax.swing.BoxLayout.Y_AXIS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import it.unibo.jumpig.view.api.MenuViewScene;

/**
 * Class that displays the game's main menu.
 */
public class MenuViewSceneImpl implements MenuViewScene {

    private static final String FRAME_TITLE = "Jumpig";
    private final JFrame frame = new JFrame(FRAME_TITLE);
    private static final String ROOT = "resources/";
    /**
     * Constructor for the main menu.
     * @throws IOException
     */
    public MenuViewSceneImpl() throws IOException {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final JPanel panelButton = new JPanel();
        panelButton.setBackground(Color.CYAN);
        panelButton.setLayout(new BoxLayout(panelButton, Y_AXIS));

        final InputStream in = Objects.requireNonNull(
            ClassLoader.getSystemResourceAsStream("settings/settings")
        );
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            line = br.readLine();
        }
        final JLabel lab2 = new JLabel(line);
        /*
         * Loading of icons and images is made very easy!
         */
        final URL imgURL = ClassLoader.getSystemResource("images/jumpigIcon.png");
        final ImageIcon icon = new ImageIcon(imgURL);
        /*
         * From now on, it's just plain GUI construction
         */
        final JLabel lab1 = new JLabel(icon);


        JButton gameButton = new JButton("START GAME");
        panelButton.add(gameButton);
        panelButton.add(Box.createRigidArea(new Dimension(0,10)));
        JButton leaderboardButton = new JButton("LEADERBOARD");
        panelButton.add(leaderboardButton);
        panelButton.add(Box.createRigidArea(new Dimension(0,10)));
        panelButton.add(new JButton("QUIT"));

        final JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(Color.CYAN);
        
        menuPanel.add(lab1);
        menuPanel.add(lab2);
        lab1.setSize(10,10);
        menuPanel.repaint();
        frame.getContentPane().add(menuPanel, BorderLayout.CENTER);

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int screenHeight = (int) screen.getHeight();
        final double percentageHeight = 0.7;
        final int height = (int) (screenHeight * percentageHeight);
        final int width = (int) (height * 0.5625);
        frame.setSize(width, height);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quit'");
    }

    /**
     * Main to test the GUI menu.
     * @param args strings
     */
    public static void main(final String[] args) throws Exception {
        new MenuViewSceneImpl().show();
    }
}
