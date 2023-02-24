package tetrisBlocks;

import Tetris.TetrisBlock;
import java.awt.*;

public class LShape extends TetrisBlock {

    public LShape() {
        super(new int[][] { { 1, 0 }, { 1, 0 }, { 1, 1 } }, Color.orange);
    }

    public String getImage() {
        return "LShape.jpg";
    }
}