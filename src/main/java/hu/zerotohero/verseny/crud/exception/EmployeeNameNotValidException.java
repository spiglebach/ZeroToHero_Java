package hu.zerotohero.verseny.crud.exception;

public class EmployeeNameNotValidException extends Exception {
    public EmployeeNameNotValidException() {
        super("Employee names must have 2 or 3 parts!");
    }
}
