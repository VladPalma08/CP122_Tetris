package tetrisBlocks;

import Tetris.TetrisBlock;
import java.awt.*;

public class ZShape extends TetrisBlock {

    public ZShape() {
        super(new int[][] { { 1, 1, 0 }, { 0, 1, 1 } }, Color.red);
    }

    public String getImage() {
        return "ZShape.jpg";
    }

}
