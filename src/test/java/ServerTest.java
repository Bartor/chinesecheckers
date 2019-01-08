import backend.GameSingleton;
import backend.socketing.Server;
import model.board.BasicBoard;
import model.board.BasicBoardMovement;
import model.exceptions.CorruptedFileException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class ServerTest {
    Server server;
    BasicBoard basicBoard;

    @Test(expected = CorruptedFileException.class)
    public void testSth() throws CorruptedFileException {
        server = new Server(2137);
        basicBoard = new BasicBoard();
        basicBoard.loadBoard(new File("NoSuchAFile.NotTxt"));
        new GameSingleton(new BasicBoardMovement(basicBoard),6);
       // server.start();

    }


}
