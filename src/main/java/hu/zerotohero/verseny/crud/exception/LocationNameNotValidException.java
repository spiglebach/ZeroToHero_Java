package hu.zerotohero.verseny.crud.exception;

public class LocationNameNotValidException extends Exception {
    public LocationNameNotValidException() {
        super("Location names must have 2 parts at most!");
    }
}
