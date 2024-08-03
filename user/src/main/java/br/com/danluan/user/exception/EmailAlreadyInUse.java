package br.com.danluan.user.exception;

public class EmailAlreadyInUse extends RuntimeException {
    public EmailAlreadyInUse() { super("Email already in use."); }
}