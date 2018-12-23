package model.player;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PiecePosition that = (PiecePosition) o;
        return compareTo(that)==0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
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

