package it.unibo.jumpig.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that represents the panel for the score.
 */
public class ScorePanel extends JPanel {
     public static final long serialVersionUID = 1L;
     /**
     * Constructor for the score.
     */
    public ScorePanel() {
          final JPanel componentsPanel = new JPanel(new FlowLayout());
          final JLabel scoreText = new JLabel("Score: ");
          final JLabel scoreNumber = new JLabel("0");
          final JLabel coinAmount = new JLabel("Coins: ");
          final JLabel coinNumber = new JLabel("0");
          final JLabel livesText = new JLabel("Lives: ");
          final JLabel livesNumber = new JLabel("3"); 
        componentsPanel.add(scoreText);
        componentsPanel.add(scoreNumber);
        componentsPanel.add(coinAmount);
        componentsPanel.add(coinNumber);
        componentsPanel.add(livesText);
        componentsPanel.add(livesNumber);
        componentsPanel.setBackground(Color.CYAN);
        this.add(componentsPanel, BorderLayout.NORTH);
     }

     /**
      * Method that updates the score.
      */
     public void refresh() {
          // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refresh'");
     }
     /*
     public static void main(final String[] args) {
          final JFrame frame = new JFrame();
          final JPanel scorePanel = new ScorePanel();
          frame.getContentPane().add(scorePanel);
          frame.setVisible(true);
     }
     */
}
