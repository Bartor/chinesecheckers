package backend;

import backend.interpreter.MessageInterpreter;
import backend.socketing.MessageQueueSingleton;
import backend.socketing.Server;
import model.board.BasicBoard;
import model.board.BasicBoardMovement;
import model.exceptions.CorruptedFileException;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        int port;
        int limit;
        BasicBoard basicBoard;

        try {
            port = Integer.parseInt(args[0]);
            limit = Integer.parseInt(args[1]);
            basicBoard = new BasicBoard();
            basicBoard.loadBoard(new File(args[2]));
        } catch (NumberFormatException e) {
            System.out.println("Format: [port] [player limit 2 | 4 | 6] [path to board file]");
            return;
        } catch (CorruptedFileException e) {
            System.out.println(e.getMessage());
            System.out.println("File was " + args[2]);
            return;
        }
        Server server = new Server(port);

        //initializing static classes
        new GameSingleton(new BasicBoardMovement(basicBoard), limit);
        new MessageInterpreter(server);

        server.start();
    }
}
