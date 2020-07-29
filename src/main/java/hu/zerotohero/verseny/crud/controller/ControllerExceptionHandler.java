package hu.zerotohero.verseny.crud.controller;

import hu.zerotohero.verseny.crud.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {
            EmptyAttributeException.class,
            EquipmentAlreadyOperatedException.class,
            EquipmentAtDifferentLocationException.class,
            EquipmentStillOperatedAtLocationException.class,
            IncompatibleJobAndEquipmentException.class,
            LocationNotEmptyException.class,
            ManagerAlreadyAtLocationException.class,
            ManagerDoesNotUseEquipmentException.class,
            InsufficientSalaryException.class,
            SalaryDifferenceException.class,
            InsufficientManagerSalaryException.class,
            EmployeeNameNotValidException.class,
            LocationNameNotValidException.class,
            LocationAddressNotValidException.class} )
    public ResponseEntity<?> handleWrongRequests(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchEntityException.class)
    public ResponseEntity<?> handleNoSuchLocationExceptions(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
