package model.board;

import model.exceptions.CorruptedFileException;

import java.io.File;

public interface BoardInterdace {
    void loadBoard(File file) throws CorruptedFileException;
    int[][] getPositions();
    void setPositions(int row, int col, int val);
}
