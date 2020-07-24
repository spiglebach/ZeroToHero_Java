package hu.zerotohero.verseny.crud.exception;

public class EquipmentAtDifferentLocationException extends Exception {
    public EquipmentAtDifferentLocationException() {
        super("The equipment with the given ID is at a different location!");
    }
}
