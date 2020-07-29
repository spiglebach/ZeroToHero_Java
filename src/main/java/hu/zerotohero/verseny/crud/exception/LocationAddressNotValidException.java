package hu.zerotohero.verseny.crud.exception;

public class LocationAddressNotValidException extends Exception {
    public LocationAddressNotValidException() {
        super("Location addresses must start with a 4 digit postal code!");
    }
}
