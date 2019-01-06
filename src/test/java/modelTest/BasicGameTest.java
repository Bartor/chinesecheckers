package modelTest;

import model.board.BasicBoard;
import model.board.BasicBoardMovement;
import model.board.BoardMovementInterface;
import model.exceptions.CannotAddPlayerException;
import model.exceptions.CorruptedFileException;
import model.exceptions.NoSuchPlayerException;
import model.game.BasicGame;
import model.player.Army;
import model.player.PiecePosition;
import model.player.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;


public class BasicGameTest {
    private BasicGame game;

    @Before
    public void setUp(){
        try {
            int limit=6;
            BoardMovementInterface boardMovementInterface = createBasicBMovementUnderTest();
            game = new BasicGame(boardMovementInterface, limit);
            game.addPlayer(new Player("1"));
            game.addPlayer(new Player("2"));
        } catch (CorruptedFileException | CannotAddPlayerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHasWon(){
        PiecePosition[] wonPositions = new PiecePosition[10];
        wonPositions[0]=new PiecePosition(0,6);
        wonPositions[1]=new PiecePosition(1,5);
        wonPositions[2]=new PiecePosition(1,6);
        wonPositions[3]=new PiecePosition(2,5);
        wonPositions[4]=new PiecePosition(2,6);
        wonPositions[5]=new PiecePosition(2,7);
        wonPositions[6]=new PiecePosition(3,4);
        wonPositions[7]=new PiecePosition(3,5);
        wonPositions[8]=new PiecePosition(3,6);
        wonPositions[9]=new PiecePosition(3,7);

        Army army = new Army(wonPositions);

        try {
            game.getPlayerById(4).setArmy(army);
            Assert.assertNotNull(game.getPlayerById(4).getArmy());
            Assert.assertTrue(game.hasWon(game.getPlayerById(4)));
            game.getPlayerById(4).setId(3);
            Assert.assertTrue(!game.hasWon(game.getPlayerById(3)));
        } catch (NoSuchPlayerException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testOrder(){
        Assert.assertEquals(4, game.getPlayers().get(1).getId());
        try {
            game.addPlayer(new Player("new"));
            Assert.assertEquals(2, game.getPlayers().get(2).getId());
            Assert.assertEquals(5, game.getNextId());
            game.addPlayer(new Player("newer"));
            Assert.assertEquals(5, game.getPlayers().get(3).getId());
            Assert.assertEquals(3, game.getNextId());
            game.addPlayer(new Player("newerer"));
            Assert.assertEquals(3, game.getPlayers().get(4).getId());
            game.addPlayer(new Player("the newewest"));
            Assert.assertEquals(6, game.getPlayers().get(5).getId());

        } catch (CannotAddPlayerException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = NoSuchPlayerException.class)
    public void getNoPlayer() throws NoSuchPlayerException {
        game.getPlayerById(5);
    }

    @Test (expected = CannotAddPlayerException.class)
    public void testAdding() throws CannotAddPlayerException {
        game.addPlayer(new Player("3"));
        game.addPlayer(new Player("4"));
        game.addPlayer(new Player("5"));
        game.addPlayer(new Player("6"));
        game.addPlayer(new Player("7")); //!
    }

    private BasicBoardMovement createBasicBMovementUnderTest() throws CorruptedFileException {
        BasicBoard board = createTestBoard();
        for(int i=0; i<17; i++)
            for(int j=0; j<13; j++){
                if(board.getBoardFields()[i][j]==-1 || board.getBoardFields()[i][j]==0)
                    board.setPositions(i,j,0);
                board.setPositions(i,j,board.getBoardFields()[i][j]);
            }
        return new BasicBoardMovement(board);
    }

    private BasicBoard createTestBoard() throws CorruptedFileException {
        BasicBoard testBoard = new BasicBoard();
        testBoard.loadBoard(new File("basicBoard.txt"));
        return testBoard;
    }

}
