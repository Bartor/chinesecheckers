import backend.GameSingleton;
import backend.socketing.Server;
import model.board.BasicBoard;
import model.board.BasicBoardMovement;
import model.exceptions.CorruptedFileException;

import java.io.File;

public class ServerTest {

    public static void main(String[] args){
        Server server = new Server(2137);
        BasicBoard basicBoard = new BasicBoard();
        try {
            basicBoard.loadBoard(new File("basicBoard.txt"));
            new GameSingleton(new BasicBoardMovement(basicBoard),6);
            server.start();
        } catch (CorruptedFileException e) {
            e.printStackTrace();
        }
    }

}
