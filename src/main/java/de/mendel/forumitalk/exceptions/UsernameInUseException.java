package de.mendel.forumitalk.exceptions;

public class UsernameInUseException extends RuntimeException {

    public UsernameInUseException(String message) {
        super(message);
    }
}
