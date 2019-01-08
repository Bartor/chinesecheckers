package model.exceptions;

/***
 * Throws when we can't add any more players.
 */
public class CannotAddPlayerException extends Exception {
    private String message;

    public CannotAddPlayerException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
