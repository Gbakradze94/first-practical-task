package jmp.dto;

public class NoSuchSubscriptionException extends RuntimeException {
    public NoSuchSubscriptionException(String message) {
        super(message);
    }
}
