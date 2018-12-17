package model.player;

import model.board.BoardInterdace;
import model.exceptions.PlayerNotFullyInitializedException;

public class Player {
    private String name;
    private Army army;
    private int id = -1;

    public Player(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
        if (this.army != null) {
            this.army.setId(id);
        }
    }

    public void setArmy(Army army) {
        if (this.id != -1) {
            this.army = army;
            this.army.setId(this.id);
        }
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
