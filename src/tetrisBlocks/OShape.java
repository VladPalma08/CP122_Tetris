package tetrisBlocks;

import Tetris.TetrisBlock;
import java.awt.*;

public class OShape extends TetrisBlock {

    public OShape() {
        super(new int[][] { { 1, 1 }, { 1, 1 } }, Color.yellow);
    }

    public String getImage() {
        return "OShape.jpg";
    }
}
