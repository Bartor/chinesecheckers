package model.exceptions;

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
