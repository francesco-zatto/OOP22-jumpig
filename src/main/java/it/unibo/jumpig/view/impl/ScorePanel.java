package it.unibo.jumpig.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
     /**
     * Constructor for the score
     */
    public ScorePanel() {
        final JPanel componentsPanel = new JPanel(new FlowLayout());
        final JLabel scoreText = new JLabel("Score: ");
        final JLabel scoreNumber = new JLabel("0");
        final JLabel coinAmount = new JLabel("Coins: ");
        final JLabel coinNumber = new JLabel("0");
        componentsPanel.add(scoreText);
        componentsPanel.add(scoreNumber);
        componentsPanel.add(coinAmount);
        componentsPanel.add(coinNumber);
        componentsPanel.setBackground(Color.CYAN);
        this.add(componentsPanel, BorderLayout.NORTH);
     }
     /*
     public static void main(String[] args) {
          JFrame frame = new JFrame();
          JPanel scorePanel = new ScorePanel();
          frame.getContentPane().add(scorePanel);
          frame.setVisible(true);
     }
     */
}
