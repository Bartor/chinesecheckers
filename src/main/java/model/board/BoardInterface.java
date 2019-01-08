package model.board;

import model.exceptions.CorruptedFileException;

import java.io.File;

/***
 * Interface for all boards.
 */
public interface BoardInterface {
    /***
     * Load board from a file.
     * @param file Board file, e.g. {@link BasicBoard#loadBoard(File)}.
     * @throws CorruptedFileException If file is not correct.
     */
    void loadBoard(File file) throws CorruptedFileException;

    /***
     * Load board from an array.
     * @param boardArray Board array.
     * @throws CorruptedFileException If array is not correct.
     */
    void loadBoard(String[][] boardArray) throws CorruptedFileException;

    /***
     * Returns positions of pieces.
     * @return Positions array.
     */
    int[][] getPositions();

    /***
     * Sets an element of positions array to specified value.
     * @param row Row to be set.
     * @param col Column to be set.
     * @param val Value to set.
     */
    void setPositions(int row, int col, int val);

    /***
     * Checks if particular field is null or not.
     * @param row Row of a field.
     * @param col Column of a field.
     * @return True if it's not null, false if it is.
     */
    boolean fieldNotNull(int row, int col);

    /***
     * Gets the board fields.
     * @return Board fields.
     */
    int[][] getBoardFields();
}
