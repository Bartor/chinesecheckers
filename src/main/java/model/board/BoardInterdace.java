package model.board;

import java.io.File;

public interface BoardInterdace {
    void loadBoard(File file);
    int[][] getPositions();
    void setPositions(int row, int col, int val);
}
