/*
 * Vladimir Palma
 * 
 * AudioPlayer imports javax.sound that takes existing audio files
 * from the project folder. Using it, we assigned a string to the 
 * the name of a specific file (which sets the path of the audio).
 * Then, we assigned "sound" methods and used the getClip method to
 * preload the sounds; and these methods are later used to link
 * them to their specific path, allowing the code to play our sounds
 *
 */

package Tetris;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class AudioPlayer2 {

    // creating a file separator that allows a computer to find a specific
    // file by matching them to the names of the clips within the file folder
    private String soundsFolder = "tetrissounds" + File.separator;

    // setting the location of the needed audio files
    // note: AudioPlayer takes in specific file types (WAVs work, MP3s don't)
    private String fourClearPath = soundsFolder + "Four-line Clear.wav";
    private String lessFourClearPath = soundsFolder + "Less Than Four Clear.wav";
    private String placingBlockPath = soundsFolder + "Placing Block.wav";
    private String moveSidewardsPath = soundsFolder + "Move Sidewards.wav";
    private String rotateBlockPath = soundsFolder + "Rotate Block.wav";
    private String softDropPath = soundsFolder + "Soft Drop.wav";
    private String gameLostPath = soundsFolder + "Game Lost.wav";
    private String musicMainPath = soundsFolder + "Music - Main.wav";

    // initializing empty Clip variables for later assignment
    private Clip fourClearSound, lessFourClearSound, placingBlockSound, moveSidewardsSound,
            rotateBlockSound, softDropSound, gameLostSound, musicMainSound;

    // assigns the Clip variables with audio clips; will play their respective sound
    public AudioPlayer2() {
        try {
            // getting the clip or preview of a sound; preloaded so it can be used in game
            fourClearSound = AudioSystem.getClip();
            lessFourClearSound = AudioSystem.getClip();
            placingBlockSound = AudioSystem.getClip();
            moveSidewardsSound = AudioSystem.getClip();
            rotateBlockSound = AudioSystem.getClip();
            softDropSound = AudioSystem.getClip();
            gameLostSound = AudioSystem.getClip();
            musicMainSound = AudioSystem.getClip();

            // assigning the Clip variable to the specific path of their audio.
            // getAbsoluteFile matches the file's name in the code above to
            // the file names within the project folder
            fourClearSound.open(AudioSystem.getAudioInputStream(new File(fourClearPath).getAbsoluteFile()));
            lessFourClearSound.open(AudioSystem.getAudioInputStream(new File(lessFourClearPath).getAbsoluteFile()));
            placingBlockSound.open(AudioSystem.getAudioInputStream(new File(placingBlockPath).getAbsoluteFile()));
            moveSidewardsSound.open(AudioSystem.getAudioInputStream(new File(moveSidewardsPath).getAbsoluteFile()));
            rotateBlockSound.open(AudioSystem.getAudioInputStream(new File(rotateBlockPath).getAbsoluteFile()));
            softDropSound.open(AudioSystem.getAudioInputStream(new File(softDropPath).getAbsoluteFile()));
            gameLostSound.open(AudioSystem.getAudioInputStream(new File(gameLostPath).getAbsoluteFile()));
            musicMainSound.open(AudioSystem.getAudioInputStream(new File(musicMainPath).getAbsoluteFile()));

        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) { // catching exceptions
            e.printStackTrace();
        }
    }

    // The methods below play a specific sound when called in other classes
    // setFramePosition(0) allows the sound to be reset, available for reuse
    // the start method simply plays the sound

    public void playFourClear() {
        fourClearSound.setFramePosition(0);
        fourClearSound.start();
    }

    public void playLessFourClear() {
        lessFourClearSound.setFramePosition(0);
        lessFourClearSound.start();
    }

    public void playPlacingBlock() {
        placingBlockSound.setFramePosition(0);
        placingBlockSound.start();
    }

    public void playMoveSidewards() {
        moveSidewardsSound.setFramePosition(0);
        moveSidewardsSound.start();
    }

    public void playRotateBlock() {
        rotateBlockSound.setFramePosition(0);
        rotateBlockSound.start();
    }

    public void playSoftDrop() {
        softDropSound.setFramePosition(0);
        softDropSound.start();
    }

    public void playGameLost() {
        gameLostSound.setFramePosition(0);
        gameLostSound.start();
    }

    // this method specifically loops the sound
    // the sound is played infinitely; used as background music
    public void playMusicMain() {
        musicMainSound.setFramePosition(0);
        musicMainSound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // this method counteracts the one above
    // when called, it stops the music entirely
    public void stopMusicMain() {
        musicMainSound.stop();
    }
}
