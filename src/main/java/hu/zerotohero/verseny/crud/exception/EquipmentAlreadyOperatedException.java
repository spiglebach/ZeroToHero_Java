package hu.zerotohero.verseny.crud.exception;

public class EquipmentAlreadyOperatedException extends Exception {
    public EquipmentAlreadyOperatedException() {
        super("The equipment with the given ID is already operated!");
    }
}
