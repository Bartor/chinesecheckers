package modelTest;

import model.board.BasicBoard;
import model.board.BasicBoardMovement;
import model.exceptions.CorruptedFileException;
import model.exceptions.MoveNotAllowedException;
import model.player.Piece;
import model.player.PiecePosition;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class BasicBoardMovementTest {

    /***
     * Tests if onWinZone() works correctly
     */
    @Test
    public void testOnWinZone() throws CorruptedFileException {
            BasicBoardMovement testBoardMovement = createBasicBMovementUnderTest();
            Piece piece1 = new Piece(new PiecePosition(0,6));
            piece1.setId(1);
            Piece piece2 = new Piece(new PiecePosition(5,1));
            piece2.setId(4);
            Piece piece3 = new Piece(new PiecePosition(10,1));
            piece3.setId(2);
            Assert.assertTrue(!testBoardMovement.onWinZone(piece1));
            Assert.assertTrue(!testBoardMovement.onWinZone(piece2));
            Assert.assertTrue(testBoardMovement.onWinZone(piece3));
    }

    @Test
    public void getMovesTest1() throws CorruptedFileException {
        BasicBoardMovement board = createBasicBMovementUnderTest();
        Piece testPiece = new Piece(new PiecePosition(2,6));
        Piece testPiece2 = new Piece(new PiecePosition(3,5));
       // board.getMoves(testPiece);
        Assert.assertEquals(2, board.getMoves(testPiece).length );
        Assert.assertEquals(2, board.getMoves(testPiece2).length);
    }
    @Test
    public void getMovesTest2() throws CorruptedFileException {
        BasicBoardMovement board = createBasicBMovementUnderTest();
        Piece testPiece = new Piece (new PiecePosition(3,5));
        Piece testPiece2 = new Piece (new PiecePosition(3,4));
        try {
            board.makeMove(testPiece, new PiecePosition(4,5));
            Assert.assertEquals(3, board.getMoves(testPiece2).length);
        } catch (MoveNotAllowedException e) {
            e.printStackTrace();
        }
    }

    /***
     * Tests making move
     * @throws MoveNotAllowedException
     */
    @Test
    public void testMakeMovePos() throws MoveNotAllowedException {
        try {
            BasicBoardMovement testBoardMovement = createBasicBMovementUnderTest();
            Piece piece = new Piece(new PiecePosition(2,6));
            piece.setId(1);
            testBoardMovement.makeMove(piece, new PiecePosition(4,5));
            Assert.assertArrayEquals(new int[]{-1,-1,-1,-1,-1,1,0,1,-1,-1,-1,-1,-1}, testBoardMovement.getBoard().getPositions()[2]);
            Assert.assertArrayEquals(new int[]{6,6,6,6,0,1,0,0,0,2,2,2,2}, testBoardMovement.getBoard().getPositions()[4]);

        } catch (CorruptedFileException e) {
            e.printStackTrace();
        }
    }

    /***
     * Tests making move (incorrect position)
     * @throws MoveNotAllowedException
     */
    @Test(expected = MoveNotAllowedException.class)
    public void testMakeMoveNeg() throws MoveNotAllowedException {
        try {
            BasicBoardMovement testBoardMovement = createBasicBMovementUnderTest();
            Piece piece = new Piece(new PiecePosition(0,3));
            testBoardMovement.makeMove(piece, new PiecePosition(2,3));

        } catch (CorruptedFileException e) {
            e.printStackTrace();
        }
    }

    /***
     * Tests making move after jump
     * @throws MoveNotAllowedException
     */
    @Test(expected = MoveNotAllowedException.class)
    public void makeMoveAfterJump() throws MoveNotAllowedException {
        try {
            BasicBoardMovement testBoardMovement = createBasicBMovementUnderTest();
            Piece piece = new Piece(new PiecePosition(0,3));
            testBoardMovement.makeMove(piece, new PiecePosition(2,3));

        } catch (CorruptedFileException e) {
            e.printStackTrace();
        }
    }

    protected BasicBoardMovement createBasicBMovementUnderTest() throws CorruptedFileException {
        BasicBoard board = createTestBoard();
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++){
                if(board.getBoardFields()[i][j]==-1 || board.getBoardFields()[i][j]==0)
                    board.setPositions(i,j,0);
                board.setPositions(i,j,board.getBoardFields()[i][j]);
            }
        return new BasicBoardMovement(board);
    }

    protected BasicBoard createTestBoard() throws CorruptedFileException {
        BasicBoard testBoard = new BasicBoard();
        testBoard.loadBoard(new File("basicBoard.txt"));
        return testBoard;
    }
}