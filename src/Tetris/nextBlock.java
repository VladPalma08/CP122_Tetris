/*
 * Vladimir Palma
 * 
 * nextBlock is a class that extends JPanel
 * This sets the panel that is used as a background behind the next displayed block
 * 
 */

package Tetris;

import javax.swing.*;
import java.awt.*;

public class nextBlock extends JPanel {

    public nextBlock() {
        // setting the bounds and background color of the JPanel
        this.setBounds(520, 410, 210, 290);
        this.setBackground(Color.black);
    }

}
