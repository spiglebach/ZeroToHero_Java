package hu.zerotohero.verseny.crud.exception;

public class InsufficientSalaryException extends Exception {
    public InsufficientSalaryException() {
        super("Employee salary must be at least 300!");
    }
}
