package model.board;

import java.io.File;

public class BasicBoard implements BoardInterdace {
    /***
     * e.g.
     * n n 1 1 0 0 0
     * n 1 1 0 2 2 n
     * 0 0 0 2 2 n n
     */
    private int[][] boardFields;
    /***
     * e.g.
     * 0 0 1 0 0 1 0
     * 0 2 1 2 0 0 0
     * 0 1 0 2 2 0 0
     */
    private int[][] positions;
    public void loadBoard(File file) {
        //todo implement loadBoard from a text file
    }

    public int[][] getPositions() {
        return this.positions;
    }

    public void setPositions(int row, int col, int val) {
        this.positions[row][col] = val;
    }
}
