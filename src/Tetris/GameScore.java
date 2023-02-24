/*
 * Vladimir Palma
 * 
 * The GameScore is a class that extends JPanel
 * This sets the panel that is used to implement the game score...
 * in GameForm, using the background and size defined here
 *
 */

package Tetris;

import javax.swing.JPanel;
import java.awt.*;

public class GameScore extends JPanel {

    public GameScore() {
        // setting the bounds and background color of the JPanel
        this.setBounds(535, 168, 175, 40);
        this.setBackground(Color.black);
    }

}
