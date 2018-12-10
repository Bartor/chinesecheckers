package model.player;

public class PiecePosition implements Comparable<PiecePosition> {
    private int row;
    private int col;
    public PiecePosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int compareTo(PiecePosition o) {
        return !(o.getCol() == this.getCol() && o.getRow() == this.getRow()) ? 1 : 0;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
