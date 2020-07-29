package hu.zerotohero.verseny.crud.exception;

public class ManagerSalaryException extends Exception {
    public ManagerSalaryException() {
        super("A manager's salary must not be lower than any other employee's at its location!");
    }
}
