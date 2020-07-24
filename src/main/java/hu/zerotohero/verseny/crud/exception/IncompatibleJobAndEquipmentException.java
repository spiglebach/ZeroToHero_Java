package hu.zerotohero.verseny.crud.exception;

public class IncompatibleJobAndEquipmentException extends Exception {
    public IncompatibleJobAndEquipmentException() {
        super("The given equipment is not suitable for the specified job!");
    }
}
