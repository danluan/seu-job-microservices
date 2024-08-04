package br.com.danluan.user.exception;

public class WorkerIdAlreadyInUseException extends RuntimeException {
    public WorkerIdAlreadyInUseException() {
        super("Worker already has a resume");
    }
}
