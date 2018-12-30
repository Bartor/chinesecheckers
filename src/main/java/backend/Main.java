package backend;

import backend.interpreter.MessageInterpreter;
import backend.socketing.Server;
import model.board.BasicBoard;
import model.board.BasicBoardMovement;
import model.exceptions.CorruptedFileException;

import java.io.File;

/***
 * Starts things off.
 */
public class Main {
    public static void main(String[] args) {
        int port;
        int limit;
        BasicBoard basicBoard;

        if (args.length != 3) {
            System.out.println("Format: [port] [player limit 2 | 4 | 6] [path to board file]");
            return;
        }
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
        if (limit != 2 && limit != 4 && limit != 6) {
            System.out.println("Limit has to be 2, 4 or 6");
            return;
        }

        Server server = new Server(port);

        //initializing static classes
        new GameSingleton(new BasicBoardMovement(basicBoard), limit);

        server.start();
    }
}
