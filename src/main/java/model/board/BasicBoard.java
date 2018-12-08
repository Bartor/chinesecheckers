package model.board;

import model.exceptions.CorruptedFileException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

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
    public void loadBoard(File file) throws CorruptedFileException {
        try {
            Scanner s = new Scanner(file);
            int lineCounter = 0;
            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(" ");
                this.boardFields[lineCounter] = new int[line.length];
                for (int i = 0; i < line.length; i++) {
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

    public int[][] getPositions() {
        return this.positions;
    }

    public void setPositions(int row, int col, int val) {
        this.positions[row][col] = val;
    }

    //potrzebna, zakładając, że tylko boardFields ma info o kstzałcie ( -1, bo chyba łatwiej od nulli (?))
    public boolean fieldNotNull(int x, int y){
        if(boardFields[x][y]==-1) return false;
        else return true;
    }
}
