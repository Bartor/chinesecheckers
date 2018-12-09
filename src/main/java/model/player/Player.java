package model.player;

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
            throw new PlayerNotFullyInitializedException("Canno set id without an army");
        }
    }

    /***
     * Checks if player has won.
     * @return True if he did, false if not.
     */
    public boolean won() {
        //todo implement
        return false;
    }
}
