package tetrisBlocks;

import Tetris.TetrisBlock;
import java.awt.*;

public class TShape extends TetrisBlock {

    public TShape() {
        super(new int[][] { { 1, 1, 1 }, { 0, 1, 0 } }, new Color(102, 0, 153));
    }

    public String getImage() {
        return "TShape.jpg";
    }

}
