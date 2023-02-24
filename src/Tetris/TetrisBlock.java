/*
 * Vladimir Palma
 * 
 * The TetrisBlock class creates the blocks within the game, this is where the information 
 * about a given block is stored and how you access them. most of this class is getters and setters 
 * however it does contain an abstract class for the getImage method and created the rotated
 * verson of all the blocks.
 */

package Tetris;

import java.awt.*;
import java.util.Random;
import java.awt.image.BufferedImage;

public abstract class TetrisBlock {
    private int[][] shape;
    private Color color;
    private int x, y;
    private int[][][] shapes;
    private int currentRotation;
    protected BufferedImage img;

    // constructor for creating a new block
    public TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;

        initShapes();
    }

    // creates 4 versons of each shape, which is each of their 4 rotations
    private void initShapes() {
        shapes = new int[4][][];

        for (int i = 0; i < 4; i++) {
            int r = shape[0].length;
            int c = shape.length;

            shapes[i] = new int[r][c];

            for (int y = 0; y < r; y++) {
                for (int x = 0; x < c; x++) {
                    shapes[i][y][x] = shape[c - x - 1][y];
                }
            }

            shape = shapes[i];
        }

    }

    // this spawns a new block with the a random rotation and sets it placement
    // above the GameArea
    public void spawn(int gridWidth) {

        Random r = new Random();
        currentRotation = r.nextInt(shape.length);
        shape = shapes[currentRotation];

        y = -getHeight();
        x = (gridWidth / 2);

    }

    // accessor for the shape
    public int[][] getShape() {
        return shape;
    }

    // accessor for the color
    public Color getColor() {
        return color;
    }

    // accessor for the height
    public int getHeight() {
        return shape.length;
    }

    // accessor for the width
    public int getWidth() {
        return shape[0].length;
    }

    // accessor for the x position of the block
    public int getX() {
        return x;
    }

    // sets x to a new x position
    public void setX(int newX) {
        x = newX;
    }

    // accessor for the y position of the block
    public int getY() {
        return y;
    }

    // sets y to a new y position
    public void setY(int newY) {
        y = newY;
    }

    // allows the block to move down the game frame
    public void moveDown() {
        y++;
    }

    // allows the block to be moved to the left
    public void moveLeft() {
        x--;
    }

    // allows the block to be moved to the right
    public void moveRight() {
        x++;
    }

    // this rotates the block as well as restarts the rotation if the user continues
    // to rotate it past the 4th version
    public void rotate() {
        currentRotation++;
        if (currentRotation > 3) {
            currentRotation = 0;
        }
        shape = shapes[currentRotation];
    }

    // accessor to get the bottom edge of the block
    public int getBottomEdge() {
        return y + getHeight();
    }

    // accessor to get the left edge of the block
    public int getLeftEdge() {
        return x;
    }

    // accessor to get the right edge of the block
    public int getRightEdge() {
        return x + getWidth();
    }

    // because each block returns their own image we made this method abstract so
    // that each block can have the method but all be different
    public abstract String getImage();

}
