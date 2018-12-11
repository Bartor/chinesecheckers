package model.player;

import model.board.BoardInterdace;
import model.exceptions.PlayerNotFullyInitializedException;

public class Player {
    private String name;
    private Army army;
    private int id;

    public Player(String name) {
        this.name = name;
    }

    public void setId(int id) throws PlayerNotFullyInitializedException {
        this.id = id;
        if (this.army != null) {
            this.army.setId(id);
        } else {
            throw new PlayerNotFullyInitializedException("Cannot set id without an army");
        }
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Army getArmy() {
        return army;
    }
}
