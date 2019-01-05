package bot.logic;

import bot.networking.MessageInterpreter;
import frontend.controllers.Game;
import javafx.application.Platform;
import model.player.Player;

public class BasicBotLogic {
    //todo uzupełnić pola i funkcje, i logikę, itd.

    private Game game;
    private Player thisPlayer;
    //itd., tu trzymać model gry

    public BasicBotLogic() {
        //...to by się w sumie przydało gdzieś inicjować jako instancję, bo wszystko statyczne już mnie boli
        //w ogóle potem przebuduję tę aplikację, aby przestałą być statyczna wszędzie
    }

    //funkcje wywoływane w razie aktualnej tury bota
    public void makeMove() {
        MessageInterpreter.getMessageQueue().add("info dla serwera o ruchu");
    }

    //ruchy w bocie wykonywać samym getGame().getMovementInterface().makeMove(...) z poziomu Interpretera
    public Game getGame() {
        return game;
    }
}
