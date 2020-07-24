package hu.zerotohero.verseny.crud.exception;

public class LocationNotEmptyException extends Exception {
    public LocationNotEmptyException() {
        super("The location you want to delete still has equipments or employees!");
    }
}
