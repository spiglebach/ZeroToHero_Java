package hu.zerotohero.verseny.crud.exception;

public class InsufficientManagerSalaryException extends Exception {
    public InsufficientManagerSalaryException() {
        super("A manager's salary must not be lower than any other employee's at its location!");
    }
}
