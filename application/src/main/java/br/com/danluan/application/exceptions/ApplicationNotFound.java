package br.com.danluan.application.exceptions;

public class ApplicationNotFound extends RuntimeException {
    public ApplicationNotFound() { super("Application not found."); }
}
