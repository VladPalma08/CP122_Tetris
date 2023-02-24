/*
 * Vladimir Palma
 * 
 * GameForm is our main JFrame; it is where all of out game components come together to
 * display tetris. In this class we read user keypresses so that they are able
 * to move blocks left, right and down as well as rotate them. There are many JPanels 
 * added to the JFrame and this is also where the startGame method is called. 
 *
 */

package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameForm extends JFrame implements ActionListener {
    private GameArea ga;
    private GameScore gs;
    private GameLevel gl;
    private GameMenuButtons gmb;
    private GameThread gt;
    private nextBlock nb;

    // makes up the user interface of the game (includes all the elements) this is
    // our MAIN JFrame
    public GameForm() {

        // setting background and layout of the WHOLE Jframe
        JLabel background;
        ImageIcon img;

        this.setTitle("Team Tetris: TETRIS");
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(790, 962));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ImageIcon img = new ImageIcon("Background4.jpg");
        img = new ImageIcon(getClass().getClassLoader().getResource("Background4.jpg"));

        background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 800, 938);

        // creates a new instance of the JPanel GameArea
        ga = new GameArea();

        // creates a new instance of the JPanel GameScore and formattes it
        gs = new GameScore();
        displayScore.setFont(new Font("SansSerif", Font.BOLD, 20));
        displayScore.setForeground(Color.white);
        gs.add(displayScore); // displaying user's score, method found below

        // creates a new instance of the JPanel GameLevel and formattes it
        gl = new GameLevel();
        displayLevel.setFont(new Font("SansSerif", Font.BOLD, 20));
        displayLevel.setForeground(Color.white);
        gl.add(displayLevel); // displaying game's current level, method found below

        // creates a new instance of the JPanel GameMenuButtons which is the JPanel that
        // the menu button sits on top of
        gmb = new GameMenuButtons();
        JButton menu = new JButton("Menu"); // makes a new menu button
        menu.setFont(new Font("SansSerif", Font.BOLD, 12));
        menu.setFocusable(false);
        menu.setPreferredSize(new Dimension(200, 30));
        menu.addActionListener(this); // ActionLister that will change to the menu when the button is clicked by the
                                      // mouse

        // creates a new instance of the JPanel nextBlock so that we are able to display
        // the next block
        nb = new nextBlock();

        // adds nextBlock panel to the MAIN JFrame
        this.add(nb);

        // adds menu button to GameMenuButtons panel
        gmb.add(menu);

        this.add(ga); // adds GameArea panel to the MAIN JFrame
        this.add(gs); // adds GameScore panel to the MAIN JFrame
        this.add(gl); // adds GameLevel panel to the MAIN JFrame
        this.add(gmb); // adds GameMenuButtons panel to the MAIN JFrame
        this.add(background); // adds background to the MAIN JFrame

        this.setLocationRelativeTo(null); // centers GUI on the screen

        initControls(); // initates the controls which is a method defined below

    }

    // actionperfromed for the actionlistener on the menu button
    public void actionPerformed(ActionEvent e) {

        gt.interrupt(); // it interrupts GameThread stopping its infinate loop
        this.setVisible(false); // sets the visability of the MAIN JFrame to false (makes it disapear)
        Tetris.showStartingMenu(); // makes the starting menu visible
        Tetris.stopMusicMain(); // stops the background music
    }

    // this is what we use to get the next block and display it, this method is
    // called within the infinate loop in GameThread so that it is continuously
    // running
    public void Update() {
        TetrisBlock block = ga.getNextBlock(); // new instance of a tetric block and gets the nextblock
        JLabel jl = new JLabel();
        jl.setIcon(new ImageIcon(block.getImage())); // each block returns its own image so based on block will return
                                                     // the specific image
        validate(); // allows the image to print on the JLabel
        nb.removeAll(); // then clears the JPanel so that a new JLabel can be placed on top of it.
                        // Making sure that it is changing the image
        nb.add(jl);
        jl.revalidate();
        nb.repaint();

    }

    // initializing user input controls, the movement of blocks
    private void initControls() {
        InputMap im = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();

        // takes the input on the keyboard and sets it to a specific string on the
        // action map
        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");
        im.put(KeyStroke.getKeyStroke("SPACE"), "space");

        // defines the right arrow key, moves block to the right and play sound
        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.playMoveSidewards();
                ga.moveBlockRight();
            }
        });

        // defines the left arrow key, moves block to the left and play sound
        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.playMoveSidewards();
                ga.moveBlockLeft();
            }
        });

        // defines the up arrow key, rotates the block
        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.rotateBlock();
            }
        });

        // defines the down arrow key, "soft drops" the block
        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.softDropBlock();
            }
        });

        // defines the space key, "hard drops" the block
        am.put("space", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.hardDropBlock();
            }
        });

    }

    // to start the GameThread class using GameArea and GameForm as parameters, this
    // runs how fast the blocks drop, updates next block and plays music
    public void startGame() {
        ga.initBackgroundArray();
        gt = new GameThread(ga, this);
        gt.start();
    }

    // gets the score form GameThread where it is stored because most classes do not
    // have access to GameThread and we cannot create more than one instance of
    // GameThread in our code
    public int getScore() {
        return gt.getScore();
    }

    // intiates gameThread using game area as a parameter, we use this because there
    // are times where we need to start the game with a given instance GameArea
    public void startGame(GameArea ga) {
        new GameThread(ga, this).start();
    }

    // new JLabel to display the number of lines cleared by the user sets the label
    // to a defult lines cleared
    JLabel displayScore = new JLabel("Lines Cleared: 0");

    // updates player score when it changes (in this case lines cleared)
    public void updateScore(int userScore) {
        displayScore.setText("Lines Cleared: " + userScore);
    }

    // new JLabel to display the game's current level which defaults to zero
    JLabel displayLevel = new JLabel("Level: 0");

    // updates and displayes the new level, when player advances to a new level
    public void updateLevel(int level) {
        displayLevel.setText("Level: " + level);
    }

}
