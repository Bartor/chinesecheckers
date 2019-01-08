package model.exceptions;

/***
 * Thrown when trying to fetch a piece that doesn't exists in a game.
 */
public class NoSuchPieceException extends Exception {
    private String message;

    public NoSuchPieceException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
