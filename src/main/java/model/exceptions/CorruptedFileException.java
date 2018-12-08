package model.exceptions;

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
