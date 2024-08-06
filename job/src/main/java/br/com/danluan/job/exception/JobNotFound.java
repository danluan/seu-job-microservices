package br.com.danluan.job.exception;

public class JobNotFound extends RuntimeException {
    public JobNotFound() { super("Job not found."); }
}