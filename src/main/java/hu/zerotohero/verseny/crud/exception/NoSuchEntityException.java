package hu.zerotohero.verseny.crud.exception;

public class NoSuchEntityException extends Exception {
    public NoSuchEntityException(Class entitiyClass) {
        super(String.format("No %s found with the specified ID!", entitiyClass.getSimpleName()));
    }
}
