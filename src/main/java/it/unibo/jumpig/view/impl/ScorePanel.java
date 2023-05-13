package it.unibo.jumpig.view.impl;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that represents the panel for the score.
 */
public class ScorePanel extends JPanel {
     public static final long serialVersionUID = 1L;
     private final JLabel scoreText = new JLabel("Score: ");
     private final JLabel scoreNumber = new JLabel("0");
     private final JLabel coinText = new JLabel("Coins: ");
     private final JLabel coinNumber = new JLabel("0");
     private final JLabel livesText = new JLabel("Lives: ");
     private final JLabel livesNumber = new JLabel("3"); 
     /**
     * Constructor for the score.
     */
    public ScorePanel() {
          super(new FlowLayout());
          this.add(scoreText);
          this.add(scoreNumber);
          this.add(coinText);
          this.add(coinNumber);
          this.add(livesText);
          this.add(livesNumber);
          this.setBackground(Color.CYAN);
     }

     /**
      * Method that updates the score.
      * @param height the maximum height reached
      * @param coins the number of coins gained
      * @param lives the lives left
      */
     public void refresh(final int height, final int coins, final int lives) {
          this.scoreNumber.setText(Integer.toString(height));
          this.coinNumber.setText(Integer.toString(coins));
          this.livesNumber.setText(Integer.toString(lives));
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
