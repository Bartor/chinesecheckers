package model.player;

public class PiecePosition implements Comparable<PiecePosition> {
    private int row;
    private int col;
    public PiecePosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int compareTo(PiecePosition o) {
        return !(o.getCol() == this.col && o.getRow() == this.row) ? 1 : 0;
    }

    @Override
    public String toString() {
        return "(" + this.getRow() + " " + this.getCol() + ")";
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

}

