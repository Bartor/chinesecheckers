package model.player;

import java.util.Objects;

/***
 * Utility class to make position alterations easier.
 */
public class PiecePosition implements Comparable<PiecePosition> {
    /***
     * Row of the position.
     */
    private int row;
    /***
     * Column of the position.
     */
    private int col;

    /***
     * New position on given coordinates.
     * @param row Row of the position.
     * @param col Column of the position.
     */
    public PiecePosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /***
     * Compares if positions are equal.
     * @param o Position we compare tu.
     * @return 0 if they are, 1 otherwise.
     */
    @Override
    public int compareTo(PiecePosition o) {
        if (this.col == o.getCol() && this.row == o.getRow()) {
            return 0;
        } else {
            return 1;
        }
    }

    /***
     * Cehckes if positions are equal.
     * @param o Position we compare to.
     * @return True if they are, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PiecePosition that = (PiecePosition) o;
        return compareTo(that) == 0;
    }

    /***
     * Hashes pair of this row and col.
     * @return Hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    /***
     * Returns this as a string.
     * @return "(row, col)".
     */
    @Override
    public String toString() {
        return "(" + this.getRow() + " " + this.getCol() + ")";
    }

    /***
     * Gets column.
     * @return Column.
     */
    public int getCol() {
        return col;
    }

    /***
     * Gets row.
     * @return Row.
     */
    public int getRow() {
        return row;
    }

}

