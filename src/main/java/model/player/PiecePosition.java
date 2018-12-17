package model.player;

public class PiecePosition implements Comparable<PiecePosition> {
    private int row;
    private int col;
    public PiecePosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(PiecePosition o) {
        if (this.col == o.getCol() && this.row == o.getRow()) {
            return 0;
        } else {
            return 1;
        }
    }

    public boolean equals(PiecePosition o) {
        return compareTo(o) == 0;
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

