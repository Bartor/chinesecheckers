package model.exceptions;

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
