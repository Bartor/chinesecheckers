import backend.GameSingleton;
import backend.interpreter.MessageInterpreter;
import backend.socketing.MessageQueueSingleton;

import model.board.BasicBoard;
import model.board.BasicBoardMovement;
import model.exceptions.CannotAddPlayerException;
import model.exceptions.CorruptedFileException;
import model.player.Player;
import org.junit.*;

import java.io.File;

public class MessageInterpreterBTest {

    BasicBoard basicBoard;

    @Before
    public void setup(){
        basicBoard = new BasicBoard();
        try {
            basicBoard.loadBoard(new File("basicBoard.txt"));
        } catch (CorruptedFileException e) {
            e.printStackTrace();
        }
        new GameSingleton(new BasicBoardMovement(basicBoard), 4);
    }

    @After
    public void emptyQueue(){
        MessageQueueSingleton.getMessages().clear();
        GameSingleton.getGame().getPlayers().clear();
    }

    @After
    public void del(){
        basicBoard=null;
    }

    @Test
    public void newClientTest(){
        String message = "{\"type\":\"new-client\",\"content\":\"exNick1\"}";
        MessageInterpreter.interpret(message);
        Assert.assertEquals("{\"type\":\"new-client\",\"content\":[\"exNick1\",1],\"to\":\"all\"}", MessageQueueSingleton.getMessages().get(0) );
    }

    @Test
    public void moveTest1(){
        String message = "{\"type\":\"move\",\"content\":[[3,6],[4,6]],\"from\":1}";
        MessageInterpreter.interpret(message);
        Assert.assertEquals("{\"type\":\"next-turn\",\"content\":2,\"to\":\"all\"}", MessageQueueSingleton.getMessages().get(0));
    }

    @Test
    public void moveTest2(){
        String message = "{\"type\":\"move\",\"content\":[[3,6],[4,6]],\"from\":2}";
        MessageInterpreter.interpret(message);
        Assert.assertEquals("{\"type\":\"next-turn\",\"content\":4,\"to\":\"all\"}", MessageQueueSingleton.getMessages().get(0));
    }

    @Test
    public void moveTest3(){
        String message = "{\"type\":\"move\",\"content\":[[3,6],[4,6]],\"from\":4}";
        MessageInterpreter.interpret(message);
        Assert.assertEquals("{\"type\":\"next-turn\",\"content\":5,\"to\":\"all\"}", MessageQueueSingleton.getMessages().get(0));
    }

    @Test
    public void moveTest4(){
        String message = "{\"type\":\"move\",\"content\":[[3,6],[4,6]],\"from\":5}";
        MessageInterpreter.interpret(message);
        Assert.assertEquals("{\"type\":\"next-turn\",\"content\":1,\"to\":\"all\"}", MessageQueueSingleton.getMessages().get(0));
    }

    @Test
    public void someoneWonTest(){
        Player pl2 = new Player("2");
        Player pl3 = new  Player("3");
        Player pl4 = new Player("4");
        try {
            GameSingleton.getGame().addPlayer(pl2);
            GameSingleton.getGame().addPlayer(pl3);
            GameSingleton.getGame().addPlayer(pl4);

        } catch (CannotAddPlayerException e) {
            e.printStackTrace();
        }
        GameSingleton.getGame().createArmy(pl2);
        GameSingleton.getGame().createArmy(pl3);
        GameSingleton.getGame().createArmy(pl4);
        GameSingleton.getWinners().add(pl2);
        GameSingleton.getWinners().add(pl4);

        String message = "{\"type\":\"move\",\"content\":[[3,6],[4,6]],\"from\":1}";
        MessageInterpreter.interpret(message);
        Assert.assertEquals("{\"type\":\"next-turn\",\"content\":2,\"to\":\"all\"}", MessageQueueSingleton.getMessages().get(1));

    }
    @Test
    public void readyTest(){
        Player pl2 = new Player("2");
        Player pl3 = new  Player("3");
        Player pl4 = new Player("4");
        try {
            GameSingleton.getGame().addPlayer(pl2);
            GameSingleton.getGame().addPlayer(pl3);
            GameSingleton.getGame().addPlayer(pl4);

        } catch (CannotAddPlayerException e) {
            e.printStackTrace();
        }
        GameSingleton.readyPlayer(1);
        GameSingleton.readyPlayer(2);
        GameSingleton.readyPlayer(4);
        GameSingleton.readyPlayer(5);
        String msg = "{\"type\":\"ready\",\"content\":\"\",\"from\":4}";
        MessageInterpreter.interpret(msg);
        Assert.assertEquals("{\"type\":\"ready\",\"content\":4,\"to\":\"all\"}", MessageQueueSingleton.getMessages().get(0));
        Assert.assertEquals("{\"type\":\"start-game\",\"content\":\"\",\"to\":\"all\"}", MessageQueueSingleton.getMessages().get(1));
    }

}
