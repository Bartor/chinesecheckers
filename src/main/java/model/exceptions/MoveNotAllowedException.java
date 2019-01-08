package model.exceptions;

/***
 * Thrown when player attempt a not allowed move.
 */
public class MoveNotAllowedException extends Exception {
    private String message;

    public MoveNotAllowedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
