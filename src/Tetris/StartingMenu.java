/*
 *
 * Vladimir Palma
 * 
 * Displays the menu screen interface for our game
 * The class consists of two interactable JButtons
 * the buttons are assigned to a specific input as indicated by ActionEvent
 * ActionEvents and the ActionListener take user input through mouse click
 * A background image is also implemented to stylize our menu
 *
 */

package Tetris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StartingMenu extends JFrame implements ActionListener {

    // implementing JButtons to receive user input
    JButton menuStart, menuBoard, menuExit;

    ImageIcon menuback;

    public StartingMenu() {

        // new JFrame to display the menu and the elements to be implemented
        JFrame menu = new JFrame();

        // setting the background of our menu screen and its dimensions
        // the background is also assigned to a JLabel, added to Frame
        JLabel menuBackground;
        menu.setPreferredSize(new Dimension(700, 700));

        // ImageIcon menuback = new ImageIcon("MenuBackground.jpg");
        menuback = new ImageIcon(getClass().getClassLoader().getResource("MenuBackground.jpg"));

        menuBackground = new JLabel("", menuback, JLabel.CENTER);
        menuBackground.setBounds(0, 0, 700, 700);

        // creating a JPanel that the JButtons are added to
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);

        // setting Layout to "null" allows elements to be placed in specific coordinates
        panel.setLayout(null);

        // setting the font and size to the JButton text
        // the bounds of the button, or size, are also set through the setBounds method
        menuStart = new JButton("New Game");
        menuStart.setBounds(225, 400, 250, 30);
        menuStart.setFont(new Font("SansSerif", Font.BOLD, 15));
        panel.add(menuStart);
        menuStart.addActionListener(this);

        menuBoard = new JButton("Leaderboard [Under Maintenance]");
        menuBoard.setBounds(190, 440, 320, 30);
        menuBoard.setFont(new Font("SansSerif", Font.BOLD, 15));
        panel.add(menuBoard);
        menuBoard.addActionListener(this);

        menuExit = new JButton("Exit Game");
        menuExit.setBounds(225, 480, 250, 30);
        menuExit.setFont(new Font("SansSerif", Font.BOLD, 15));
        panel.add(menuExit);
        menuExit.addActionListener(this);

        menu.add(menuBackground);
        menu.add(panel);

        menu.setTitle("Team Tetris: TETRIS"); // title of the Frame, seen in the topmost bar
        menu.setDefaultCloseOperation(EXIT_ON_CLOSE); // closes the program when exited on the top right
        menu.pack(); // allows for the JFrame to be shown
        menu.setLocationRelativeTo(null); // sets the location of the entire JFrame to the middle of the screen
        menu.setVisible(true);
    }

    // these events are specific to the JButtons they are assigned to
    @Override
    public void actionPerformed(ActionEvent e) {

        // the game is run when menuStart is pressed
        // when it is pressed, the playMusicMain method is called for background music
        if (e.getSource() == menuStart) {
            this.setVisible(false);
            Tetris.start();

            GameArea ga = new GameArea();

            while (true) {
                Tetris.playMusicMain();

                // if the user loses, block is found out of bounds, then the music is stopped
                if (ga.blockOutOfBounds() == true) {
                    Tetris.stopMusicMain();
                }

            }
        }

        // when the menuExit button is pressed, the code is terminated
        if (e.getSource() == menuExit) {
            this.setVisible(false);
            System.exit(0);

        }
    }
}