package model.exceptions;

public class PlayerNotFullyInitializedException extends Exception {
    String message;
    public PlayerNotFullyInitializedException(String message) {
        this.message = message;
    }
}
