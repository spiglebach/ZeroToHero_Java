package hu.zerotohero.verseny.crud.exception;

public class EquipmentStillOperatedAtLocationException extends Exception {
    public EquipmentStillOperatedAtLocationException() {
        super("Equipment is still operated at its location!");
    }
}
