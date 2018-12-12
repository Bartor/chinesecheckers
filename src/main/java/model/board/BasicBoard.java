package model.board;

import model.exceptions.CorruptedFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BasicBoard implements BoardInterdace {

    /***
     * Model of a board.
     * Uses -1 for fields not available for players.
     * Uses 0 for fields without an owner.
     * Uses 1 to 6 for fields of particular players.
     */
    private int[][] boardFields;

    /***
     * Positions of pieces.
     * Uses 0 for nothing.
     * Uses 1 to 6 for position of particular player's pieces.
     */
    private int[][] positions;

    public BasicBoard(int rows, int columns){
        this.boardFields=new int[rows][columns];
        this.positions=new int[rows][columns];
    }

    /***
     * Loads board from a file.
     * @param file Should be a text file, using spaces and new lines to form an array-like structure.
     *             Should consist of 0 - 6 and "n" (for -1), see {@link #boardFields}.
     * @throws CorruptedFileException When files does not conform to given expectations, an exception is thrown.
     */
    public void loadBoard(File file) throws CorruptedFileException {
        try {
            Scanner s = new Scanner(file);
            int lineCounter = 0;
            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(" ");
                this.boardFields[lineCounter] = new int[line.length];
                for (int i = 0; i < line.length; i++) {
                    if (line[i].equals("n")) { //n is for null
                        boardFields[lineCounter][i] = -1;
                        continue;
                    }
                    try {
                        int field = Integer.parseInt(line[i]);
                        if (field > 6) {
                            throw new CorruptedFileException("This game supports up to six players only");
                        }
                        if (field < 0) {
                            throw new CorruptedFileException("Values should start at 0");
                        }
                        this.boardFields[lineCounter][i] = field;
                    } catch (NumberFormatException e) {
                        throw new CorruptedFileException("Values should be an integer");
                    }
                }
                lineCounter++;
            }
        } catch (FileNotFoundException e) {
            throw new CorruptedFileException("File could not be found");
        }
    }

    /***
     * Getter for positions table.
     * @return Table of positions, see {@link #positions}.
     */
    public int[][] getPositions() {
        return this.positions;
    }

    /***
     * Sets something in board's {@link #positions} table.
     * @param row Row of a change.
     * @param col Column of a change.
     * @param val Value to be set.
     */
    public void setPositions(int row, int col, int val) {
        this.positions[row][col] = val;
    }

    /***
     * Checks if a particular field is not null.
     * @param row Row to check.
     * @param col Column to check.
     * @return True, if isn't null. False if is.
     */
    public boolean fieldNotNull(int row, int col){
        int h=boardFields.length;
        int j=boardFields[0].length;
        return (j>col && h>row && row>=0 && col>=0 && boardFields[row][col] != -1);
    }

    public int[][] getBoardFields(){
        return this.boardFields;
    }
}
