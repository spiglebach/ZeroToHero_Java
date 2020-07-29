package hu.zerotohero.verseny.crud.exception;

public class SalaryDifferenceException extends Exception {
    public SalaryDifferenceException() {
        super("Salary difference greater than 20% of the average salary of employees performing the given job!");
    }
}
