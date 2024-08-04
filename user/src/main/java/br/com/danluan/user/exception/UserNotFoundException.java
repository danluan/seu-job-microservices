package br.com.danluan.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() { super("User ID not found."); }

}