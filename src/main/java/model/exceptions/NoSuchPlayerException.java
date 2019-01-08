package model.exceptions;

/***
 * Thrown when trying to fetch a piece which doesn't exist in a game.
 */
public class NoSuchPlayerException extends Exception {
    private String message;

    public NoSuchPlayerException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
