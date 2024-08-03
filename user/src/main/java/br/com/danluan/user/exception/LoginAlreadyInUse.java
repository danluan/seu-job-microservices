package br.com.danluan.user.exception;

public class LoginAlreadyInUse extends RuntimeException {
    public LoginAlreadyInUse() { super("Login already in use."); }
}
