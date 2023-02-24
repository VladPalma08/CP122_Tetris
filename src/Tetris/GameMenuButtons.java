/*
 * Vladimir Palma
 * 
 * The GameMenuButtons is a class that extends JPanel
 * This sets the panel that is used to implement the menu button in the GameForm
 * background and size defined here
 *
 */

package Tetris;

import javax.swing.JPanel;
import java.awt.*;

public class GameMenuButtons extends JPanel {

    public GameMenuButtons() {
        // sets the background and bounds of the JPanel, making the edges clear
        this.setBounds(523, 810, 200, 40);
        this.setOpaque(false);
        this.setBackground(new Color(0, 0, 0, 1));
    }

}
