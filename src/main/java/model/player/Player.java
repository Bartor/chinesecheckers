package model.player;

public class Player {
    private String name;
    private Army army;
    private int id;

    public Player(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
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
