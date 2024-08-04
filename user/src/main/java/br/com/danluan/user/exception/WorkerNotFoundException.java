package br.com.danluan.user.exception;

public class WorkerNotFoundException extends RuntimeException {
    public WorkerNotFoundException() { super("Worker ID not found."); }
}
