package model.exceptions;

/**
 * Thrown when trying to add a not fully initialized player to a game.
 */
public class PlayerNotFullyInitializedException extends Exception {
    String message;

    public PlayerNotFullyInitializedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
