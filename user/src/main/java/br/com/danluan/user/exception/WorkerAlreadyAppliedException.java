package br.com.danluan.user.exception;

public class WorkerAlreadyAppliedException extends RuntimeException{
    public WorkerAlreadyAppliedException() {
        super("Worker already applied.");
    }
}
