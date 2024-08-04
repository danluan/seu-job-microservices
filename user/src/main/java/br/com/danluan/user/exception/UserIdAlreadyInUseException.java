package br.com.danluan.user.exception;

public class UserIdAlreadyInUseException extends RuntimeException {
    public UserIdAlreadyInUseException() { super("User ID already is a Worker."); }
}
