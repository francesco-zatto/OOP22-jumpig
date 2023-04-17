package it.unibo.jumpig.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

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
        componentsPanel.add(scoreText);
        componentsPanel.add(scoreNumber);
        componentsPanel.setBackground(Color.CYAN);
        this.add(componentsPanel, BorderLayout.NORTH);
     }
}
