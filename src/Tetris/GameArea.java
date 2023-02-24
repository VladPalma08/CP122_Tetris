/*
 * Vladimir Palma
 * 
 * This is our GameArea which is a JPanel that will exist within out main JFrame, GameForm.
 * Our goal here is to create a screen that will display the blocks and show them moving, rotating
 * dropping etc. This is where we have called all our methods to check whether a block can rotate,
 * move left or right, move down, and hard drop. this is also where we genereate the next block. We
 * create the first block while simultaniously generating the next block so that we are able to display 
 * it in out JPanel nextBlock. JPanel is part of java swing and is a changeable display within a already
 * set JFrame.
 *
 */

package Tetris;

import javax.swing.*;

import tetrisBlocks.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameArea extends JPanel {

    // initializing variables needed for calculating the game area's grid
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private Color[][] background;
    int blockIndex;

    // initializing a variable of class TetrisBlock to spawn blocks into the grid
    TetrisBlock block;
    ArrayList<TetrisBlock> blocks;
    TetrisBlock nextShape;

    // constructor to define the "game area", or the bounds of the Tetris game and
    // grid
    public GameArea() {
        this.setBounds(47, 69, 400, 800);
        this.setBackground(Color.black);

        gridColumns = 10;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;

        blocks = new ArrayList<TetrisBlock>(); // creates an arraylist of tetrisblock blocks

        blocks.add(new IShape());
        blocks.add(new JShape());
        blocks.add(new LShape());
        blocks.add(new OShape());
        blocks.add(new SShape());
        blocks.add(new TShape());
        blocks.add(new ZShape());

        setBlockIndex(); // randomly generates new block so that we are able to get the first block in
                         // the game
        nextShape = blocks.get(blockIndex);
    }

    //
    public void initBackgroundArray() {
        background = new Color[gridRows][gridColumns];
    }

    // gets the block at a specific index. we use this to display next block
    public TetrisBlock getBlockAt(int index) {
        return blocks.get(index);
    }

    // spawns the block in the game area
    public void spawnBlock() {
        block();
        block.spawn(gridColumns);

    }

    // sets nextshape to block and then generates a new next shape
    public void block() {
        block = nextShape;
        setNextShape();
    }

    // creates the new next shape
    public void setNextShape() {
        setBlockIndex();
        nextShape = blocks.get(blockIndex);

    }

    // gets the next shape. used so that we can display next block
    public TetrisBlock getNextBlock() {
        return nextShape;
    }

    // this gets a random int for a random block within the block arraylist
    public void setBlockIndex() {
        Random r = new Random();
        blockIndex = r.nextInt(blocks.size());
    }

    // accessor for blockindex
    public int getBlockIndex() {
        return blockIndex;
    }

    // checks whether a block has reached the end of the grid, bottom of the game
    private boolean checkBottom() {
        if (block.getBottomEdge() == gridRows) {
            return false; // if the bottom of the block is at the last gridrow (20) the block method will
                          // return false for check bottom.
        }

        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();

        for (int col = 0; col < w; col++) {
            for (int row = h - 1; row >= 0; row--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY() + 1; // increase in Y because this is the way the block moves when going
                                                    // down
                    if (y < 0) {
                        break;
                    }
                    if (background[y][x] != null) {
                        return false; // if the background has block in it it will also return false
                    }
                    break;
                }
            }
        }
        return true; // when the block has nothing around it it will return true as in there is
                     // nothing at the bottom of the block
    }

    // checks whether the block can move/rotate to the left. returns true or false
    private boolean checkLeft() {
        if (block.getLeftEdge() == 0) {
            return false; // returns false if the block edge is equal to zero or the edge of the game area
        }
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() - 1; // decrease in x becuase this is how the block moves left
                    int y = row + block.getY();
                    if (y < 0) {
                        break;
                    }
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }

        return true;
    }

    // checks whether the block can move/rotate to the right. returns true or false
    private boolean checkRight() {
        if (block.getRightEdge() == gridColumns) {
            return false;
        }
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();

        for (int row = 0; row < h; row++) {
            for (int col = w - 1; col >= 0; col--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() + 1; // increase in x because this is how a block moves right
                    int y = row + block.getY();
                    if (y < 0) {
                        break;
                    }
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    // stops the game if a block is not within the game area
    // returns true or false based on the y value of a block
    public boolean blockOutOfBounds() {
        if (block.getY() < 0) {
            block = null;
            return true;
        }
        return false;
    }

    // moves blocks down according to the checkBottom method above
    public boolean moveBlockDown() {
        if (checkBottom() == false) {
            return false; // if block does have blocks below it it will not move down
        }
        block.moveDown(); // moves block down by increasing y value
        repaint(); // repaints block to mimic block falling
        return true; // returns true for other methods
    }

    // when right arrow key is pressed moves block to the right
    public void moveBlockRight() {
        if (block == null) { // if block doesnt exist stops other if statement and move right to be performed
            return;
        }
        if (!checkRight()) { // if check right is NOT true then stops moveright from performing
            return;
        }
        block.moveRight(); // moves block to the right by increasing x value
        repaint(); // mimics animation of block moving
    }

    // when left arrow key is pressed moves block to the left
    public void moveBlockLeft() {
        if (block == null) { // if block doesnt exist stops other if statement and move left to be performed
            return;
        }
        if (!checkLeft()) { // if check left is NOT true then stops moveright from performing
            return;
        }
        block.moveLeft();// moves block to the left by decreasing x value
        repaint();
    }

    // when the block is able to me dropped down pressing the space bar
    // will drop the block rather than it falling on its own
    public void hardDropBlock() {
        if (block == null) { // if block doesnt exist stops other if statement and move down to be performed
            return;
        }
        while (checkBottom()) {// while check bottom is true the block will move down, this moves the block to
                               // a position downward until something stops it, aka hard drop
            block.moveDown();
        }
        repaint();
        Tetris.playPlacingBlock(); // plays sound of when a block is harddropped
    }

    // similar to hard drop however only moves the block by one unit, to mimic a
    // soft drop
    public void softDropBlock() {
        if (block == null) {
            return;
        }
        if (!checkBottom()) {
            return;
        }
        block.moveDown();
        repaint();
        Tetris.playSoftDrop();
    }

    // this rotates the block and checks to make sure block is not rotating out of
    // frame
    public void rotateBlock() {
        if (block == null) { // if there is no block will not do anything
            return;
        }
        block.rotate(); // rotates block

        if (block.getLeftEdge() < 0) { // if left edge is less than 0 returns the value to 0, this basically makes sure
                                       // the block cannot rotate out of frame
            block.setX(0);
        }

        if (block.getRightEdge() >= gridColumns) { // if right edge is greater than the amount of columns
            block.setX(gridColumns - block.getWidth()); // set the value of x to the grid colums minus the block width,
                                                        // stops block from rotating out of frame
        }

        if (block.getBottomEdge() >= gridRows) { // if the bottom edge is greater than the number of rows
            block.setY(gridRows - block.getHeight()); // sets the value of the y to grid rows minus grid height, stops
                                                      // block from rotating out of frame
        }
        repaint(); // mimic animation of block rotating
        Tetris.playRotateBlock(); // plays sound everytime block is rotates
    }

    // checks if all cells in a row are filled
    public int clearLines() {
        boolean lineFilled;
        int clearTracker = 0; // tracks how many block are being cleared in one move

        // checks the rows within a line grid is greater than 0,
        // if it is, then the lineFilled statement is true
        for (int i = gridRows - 1; i >= 0; i--) {
            lineFilled = true;

            for (int x = 0; x < gridColumns; x++) {
                // this check is stopped if the row is not filled
                if (background[i][x] == null) {
                    lineFilled = false;
                    break;
                }
            }
            // calls on the clearLine method, the cells are removed
            if (lineFilled) {
                clearTracker++;
                clearLine(i);
                shiftDown(i);
                clearLine(0);
                i++;
                repaint();
            }
        }
        // adding two sound effects, for clearing four or less than four lines
        if (clearTracker == 4) {
            Tetris.playFourClear();
        }
        if (clearTracker > 0) {
            Tetris.playLessFourClear();
        }
        return clearTracker;
    }

    // method to remove cells from a completed line
    private void clearLine(int x) {
        for (int i = 0; i < gridColumns; i++) {
            background[x][i] = null;
        }
    }

    // if the methods above indicate that a line is cleared, then move all blocks
    // down a cell
    private void shiftDown(int x) {
        for (int row = x; row > 0; row--) {
            for (int col = 0; col < gridColumns; col++) {
                background[row][col] = background[row - 1][col];
            }
        }
    }

    // this makes the block part of the background when it can no longer move down
    public void moveBlockToBackground() {
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();

        int xPos = block.getX();
        int yPos = block.getY();

        Color color = block.getColor();

        for (int r = 0; r < h; r++) { // for the number of rows in the block
            for (int c = 0; c < w; c++) { // for the number of columns in the block

                // if there is a colored block in the block shape then changes the color of the
                // specific grid to match the color of the dropping block
                if (shape[r][c] == 1) {
                    background[r + yPos][c + xPos] = color;
                }
            }
        }

    }

    // void method to define a block's dimensions so that it can then draw the shape
    private void drawBlock(Graphics g) {
        int h = block.getHeight();
        int w = block.getWidth();
        Color c = block.getColor();
        int[][] shape = block.getShape();

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] == 1) {

                    int x = (block.getX() + col) * gridCellSize;
                    int y = (block.getY() + row) * gridCellSize;

                    drawGridSquare(g, c, x, y);
                }
            }
        }
    }

    // this draws the background of the gameArea
    private void drawBackground(Graphics g) {

        Color color;

        for (int r = 0; r < gridRows; r++) {
            for (int c = 0; c < gridColumns; c++) {
                color = background[r][c];

                if (color != null) { // if there is a color it will draw the grid squares
                    int x = c * gridCellSize;
                    int y = r * gridCellSize;
                    drawGridSquare(g, color, x, y);
                }
            }
        }
    }

    // draws rectangles of each square that then forms a shape
    private void drawGridSquare(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, gridCellSize, gridCellSize); // draws filled squares with given colors
        g.setColor(Color.black);
        g.drawRect(x, y, gridCellSize, gridCellSize); // draws outlines in ALWAYS black
    }

    // this is what makes the gameform, it will paint all the components,
    // as well as set the grid
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (int y = 0; y < gridRows; y++) { // creates the background grid based on already inputted dimensions
            for (int x = 0; x < gridColumns; x++) {
                g.drawRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize);
                g.setColor(Color.DARK_GRAY);
            }
        }

        drawBackground(g);
        drawBlock(g);
    }

}
