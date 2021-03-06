package modelTest;

import model.board.BasicBoard;
import model.exceptions.CorruptedFileException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class BasicBoardTest {


    /***
     * Tests if loadBoard() imports board file array correctly
     */
    @Test
    public void testFileImportingCorrectness() throws CorruptedFileException {
            BasicBoard testBoard=createTestBoard();
            Assert.assertArrayEquals(new int[]{-1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1}, testBoard.getBoardFields()[0]);
            Assert.assertArrayEquals(new int[]{-1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, -1, -1}, testBoard.getBoardFields()[1]);
            Assert.assertArrayEquals(new int[]{6, 6, 6, 6, 0, 0, 0, 0, 0, 2, 2, 2, 2}, testBoard.getBoardFields()[4]);
    }

    @Test
    public void testImportingFromArray() throws CorruptedFileException {
        String[][] inBoard = { {"n","n","1","n","n"},
                {"n","1","1","1","n"},
                {"2","2", "0", "3", "3"},
                {"-1", "-1", "4", "-1", "-1"}};
        BasicBoard testBoard= new BasicBoard();
        testBoard.loadBoard(inBoard);
        Assert.assertArrayEquals(new int[]{-1,-1,1,-1,-1}, testBoard.getBoardFields()[0]);
        Assert.assertArrayEquals(new int[]{-1,1,1,1,-1}, testBoard.getBoardFields()[1]);
        Assert.assertArrayEquals(new int[]{2,2,0,3,3}, testBoard.getBoardFields()[2]);
        Assert.assertArrayEquals(new int[]{-1,-1,4,-1,-1}, testBoard.getBoardFields()[3]);
    }

    @Test(expected = CorruptedFileException.class)
    public void testImportingFromArrayErr1() throws CorruptedFileException{
        String[][] inBoard = { {"n","7","1","n","n"},
                {"n","s","1","1","n"},
                {"2","2", "0", "3", "3"},
                {"-1", "-1", "4", "-1", "-1"}};
        BasicBoard testBoard= new BasicBoard();
        testBoard.loadBoard(inBoard);
    }

    @Test(expected = CorruptedFileException.class)
    public void testImportingFromArrayErr2() throws CorruptedFileException{
        String[][] inBoard = { {"n","n","1","n","n"},
                {"n","s","1","1","n"},
                {"2","2", "0", "3", "3"},
                {"-1", "-1", "4", "-1", "-1"}};
        BasicBoard testBoard= new BasicBoard();
        testBoard.loadBoard(inBoard);
    }

    /***
     * Tests if setting position works correctly
     */
    @Test
    public void testSetPositions() throws CorruptedFileException {

            BasicBoard testBoard = createTestBoard();
            testBoard.setPositions(2,2,1);
            Assert.assertEquals(1, testBoard.getPositions()[2][2]);
            testBoard.setPositions(2,2,5);
            Assert.assertEquals(5, testBoard.getPositions()[2][2]);

    }

    /***
     * Tests if null fields are found correctly
     */
    @Test
    public void fieldNotNull() throws CorruptedFileException {

            BasicBoard testBoard = createTestBoard();
            Assert.assertTrue(testBoard.fieldNotNull(0,6));
            Assert.assertTrue(!testBoard.fieldNotNull(0,0));
    }

    /***
     * Creates example board from a file
     * @return Example Board for tests
     * @throws CorruptedFileException
     */
    protected BasicBoard createTestBoard() throws CorruptedFileException {
        BasicBoard testBoard = new BasicBoard();
        testBoard.loadBoard(new File("basicBoard.txt"));
        return testBoard;
    }
}
