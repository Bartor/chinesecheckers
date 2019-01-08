package model.exceptions;

/***
 * Thrown when file used to load something is corrupted.
 */
public class CorruptedFileException extends Exception {
    private String message;

    public CorruptedFileException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
