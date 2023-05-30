package it.unibo.jumpig.view.impl;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that represents the panel for the score.
 */
public class ScorePanel extends JPanel {
     public static final long serialVersionUID = 1L;
     private static final int FONT_SIZE = 15;
     private static final Color BACKGROUND_COLOR = new Color(154, 245, 94);
     private final Font font = new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE); 
     private final JLabel scoreText = new JLabel("Score:");
     private final JLabel scoreNumber = new JLabel("0");
     private final JLabel coinText = new JLabel(" Coins:");
     private final JLabel coinNumber = new JLabel("0");
     private final JLabel livesText = new JLabel(" Lives:");
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
          addFontToComponents();
          this.setBackground(BACKGROUND_COLOR);
     }

     /**
      * Method that sets the font for each component.
      */
     private void addFontToComponents() {
          for (final Component component : super.getComponents()) {
               component.setFont(font);
          }
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
}
