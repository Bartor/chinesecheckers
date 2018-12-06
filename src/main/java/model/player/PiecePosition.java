package model.player;

public class PiecePosition {
    private int row;
    private int col;
    public PiecePosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
