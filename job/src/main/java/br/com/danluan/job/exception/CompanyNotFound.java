package br.com.danluan.job.exception;

public class CompanyNotFound extends RuntimeException {
    public CompanyNotFound() { super("Company not found"); }
}
