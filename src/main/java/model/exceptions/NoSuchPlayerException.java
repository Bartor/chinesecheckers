package model.exceptions;

public class NoSuchPlayerException extends Exception{
    private String message;
    public NoSuchPlayerException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
