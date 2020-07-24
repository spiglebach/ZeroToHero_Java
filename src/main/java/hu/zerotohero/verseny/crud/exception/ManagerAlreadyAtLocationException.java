package hu.zerotohero.verseny.crud.exception;

public class ManagerAlreadyAtLocationException extends Exception {
    public ManagerAlreadyAtLocationException() {
        super("The targeted location already has a MANAGER!");
    }
}
