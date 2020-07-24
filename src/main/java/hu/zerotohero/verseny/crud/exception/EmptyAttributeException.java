package hu.zerotohero.verseny.crud.exception;

public class EmptyAttributeException extends Exception {
    public EmptyAttributeException() {
        super("One or more required attributes are empty or not specified");
    }
}
