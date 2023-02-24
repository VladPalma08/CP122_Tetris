/*
 * Vladimir Palma
 * 
 * The GameLevel is a class that extends JPanel
 * This sets the panel that is used to implement the game level...
 * in GameForm, using the background and size defined here
 * 
 */

package Tetris;

import javax.swing.JPanel;
import java.awt.*;

public class GameLevel extends JPanel {

    public GameLevel() {
        // setting the bounds and background color of the JPanel
        this.setBounds(533, 250, 175, 40);
        this.setBackground(Color.black);
    }

}
