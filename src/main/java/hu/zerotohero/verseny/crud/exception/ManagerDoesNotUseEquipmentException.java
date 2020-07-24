package hu.zerotohero.verseny.crud.exception;

public class ManagerDoesNotUseEquipmentException extends Exception {
    public ManagerDoesNotUseEquipmentException() {
        super("The job MANAGER does not operate any equipments!");
    }
}
