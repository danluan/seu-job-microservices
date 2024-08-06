package br.com.danluan.auth.exceptions;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException() { super("Senha inválida."); }
}

