/*
 * Vladimir Palma
 * 
 * the Tetris class is composed of classes and methods
 * that start the Tetris game, seen on the left panel of the interface
 * It calls methods from most classes within the project (GameForm, GameArea, so on...)
 *
 */

package Tetris;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

public class Tetris {

    private static GameForm gf; // creating a new GameForm
    private static StartingMenu sm; // instantiating the start menu

    private static AudioPlayer2 audio = new AudioPlayer2();

    // this starts the game by making the frame visible and starting game thread
    public static void start() {
        gf.setVisible(true);
        // starts game thread (speed of the blocks dropping, updating next block, and
        // controlling music)

        gf.startGame();
    }

    public static void showStartingMenu() {
        sm.setVisible(false);
    }

    public static void gameOverScreen() throws FileNotFoundException { // PROBLEM HERE
        JFrame frame = new JFrame();
        String data = String.valueOf(gf.getScore());

        try {
            FileWriter fw = new FileWriter("fileName.txt");
            fw.write(data); // takes user score and writes it to the given file in this case filename.txt
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner myReader = new Scanner(new File("fileName.txt"));
        while (myReader.hasNextLine()) {
            data = myReader.nextLine();
        }
        myReader.close();

        JOptionPane.showMessageDialog(frame, "You scored: " + data + "!");

        // System.out.println(playerName); // test in terminal to see if the text works
        // lf.addPlayer(playerName, score);
    }

    // starts a new GameForm and StartingMenu when interacted with
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                // lf = new LeaderboardForm();
                gf = new GameForm();
                sm = new StartingMenu();

            }
        });
    }

    // plays audio for when user clears four lines, TETRIS line
    public static void playFourClear() {
        audio.playFourClear();
    }

    // plays audio for when user clears less than four lines
    public static void playLessFourClear() {
        audio.playLessFourClear();
    }

    // plays audio for when user hard drops a block
    public static void playPlacingBlock() {
        audio.playPlacingBlock();
    }

    // plays audio for when user moves a block sidewards
    public static void playMoveSidewards() {
        audio.playMoveSidewards();
    }

    // plays audio for when user rotates a block
    public static void playRotateBlock() {
        audio.playRotateBlock();
    }

    // plays audio for when user soft drops a block
    public static void playSoftDrop() {
        audio.playSoftDrop();
    }

    // plays audio for when user goes above the play area, loss
    public static void playGameLost() {
        audio.playGameLost();
    }

    // plays audio for when the game is started through the menu screen
    public static void playMusicMain() {
        audio.playMusicMain();
    }

    public static void stopMusicMain() {
        audio.stopMusicMain();
    }
}
