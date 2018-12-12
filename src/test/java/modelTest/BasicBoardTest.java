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
            Assert.assertArrayEquals(new int[]{-1,-1,-1,1,1,-1,-1,-1}, testBoard.getBoardFields()[0]);
            Assert.assertArrayEquals(new int[]{-1,-1,1,1,1,1,-1,-1}, testBoard.getBoardFields()[1]);
            Assert.assertArrayEquals(new int[]{-1,-1,-1,4,4, -1,-1,-1}, testBoard.getBoardFields()[7]);
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
            Assert.assertTrue(testBoard.fieldNotNull(0,4));
            Assert.assertTrue(!testBoard.fieldNotNull(0,0));

    }

    /***
     * Creates example board from a file
     * @return Example Board for tests
     * @throws CorruptedFileException
     */
    protected BasicBoard createTestBoard() throws CorruptedFileException {
        BasicBoard testBoard = new BasicBoard(8,8);
        testBoard.loadBoard(new File("testBoard.txt"));
        return testBoard;
    }
}