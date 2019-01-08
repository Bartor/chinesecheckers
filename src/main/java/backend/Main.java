package backend;

import backend.socketing.MessageQueueSingleton;
import backend.socketing.Server;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("type", "load-map");
            JsonArray arr = new JsonArray();
            for (int i = 0; i < basicBoard.getBoardFields().length; i++) {
                JsonArray a = new JsonArray();
                for (int j = 0; j < basicBoard.getBoardFields()[i].length; j++) {
                    a.add(basicBoard.getBoardFields()[i][j]);
                }
                arr.add(a);
            }
            jsonObject.add("content", arr);
            jsonObject.addProperty("to", "all");
            MessageQueueSingleton.getMessages().add(jsonObject.toString());

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

        new GameSingleton(new BasicBoardMovement(basicBoard), limit);

        server.start();
    }
}
