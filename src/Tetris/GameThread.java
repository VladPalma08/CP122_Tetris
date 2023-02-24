/*
 * Vladimir Palma
 * 
 * GameThread determines the game's level of difficulty or speed
 * these values are determined by initialized values, becoming
 * increasingly more difficult through the modification of those
 * values
 *
 */

package Tetris;

import java.io.FileNotFoundException;

public class GameThread extends Thread {

    private GameArea ga;
    private GameForm gf;
    private int score = 0; // sets the initial score value to 0
    private int level = 1; // sets the intiial game level to 1
    private int scorePerLevel = 3; // when the user increases in lines cleared, the score increases

    private int pause = 1000; // setting initial values of speed, modified and used below
    private int levelSpeed = 100;

    public GameThread(GameArea ga, GameForm gf) {
        this.ga = ga;
        this.gf = gf;

        gf.updateScore(score); // update score and level is called to change the values on the right panel
        gf.updateLevel(level);

    }

    public int getScore() {
        return score;
    }

    @Override
    public void run() {
        while (true) {
            ga.spawnBlock();

            while (ga.moveBlockDown() == true) {
                gf.Update();

                // Thread.sleep() method is used to stop the execution of the current thread
                // for a specific duration of the time in this case (pause) which is previously
                // defined and after that time duration ends, the thread which is executing
                // earlier starts to execute again.
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException e) {
                    return; // stops any code after from running
                }
            }

            // checks if a block is out of bounds, runs conditions
            if (ga.blockOutOfBounds()) {
                Tetris.playGameLost(); // plays game lost music and sound effects
                Tetris.stopMusicMain();
                try {
                    Tetris.gameOverScreen(); // runs the game over screen found in Tetris
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }

            ga.moveBlockToBackground();
            score += ga.clearLines(); // score increases the more the user clears lines
            gf.updateScore(score);

            int tetrisLevel = score / scorePerLevel + 1;
            if (tetrisLevel > level) {
                level = tetrisLevel;
                gf.updateLevel(level); // updates the level
                pause -= levelSpeed; // increases the game's speed accordingly
            }
        }
    }
}
