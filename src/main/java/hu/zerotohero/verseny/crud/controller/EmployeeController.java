package hu.zerotohero.verseny.crud.controller;

import hu.zerotohero.verseny.crud.dto.EmployeeDTO;
import hu.zerotohero.verseny.crud.exception.*;
import hu.zerotohero.verseny.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "")
    public ResponseEntity<?> getEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> newEmployee(EmployeeDTO employeeDTO)
            throws EmptyAttributeException,
                ManagerAlreadyAtLocationException,
                ManagerDoesNotUseEquipmentException,
                NoSuchEntityException,
                EquipmentAtDifferentLocationException,
                IncompatibleJobAndEquipmentException,
                EquipmentAlreadyOperatedException,
                InsufficientSalaryException,
                SalaryDifferenceException,
            InsufficientManagerSalaryException,
                EmployeeNameNotValidException {
        return new ResponseEntity<>(employeeService.newEmployee(employeeDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO)
            throws EmptyAttributeException,
            ManagerAlreadyAtLocationException,
            ManagerDoesNotUseEquipmentException,
            NoSuchEntityException,
            EquipmentAtDifferentLocationException,
            IncompatibleJobAndEquipmentException,
            EquipmentAlreadyOperatedException,
            InsufficientSalaryException,
            SalaryDifferenceException,
            InsufficientManagerSalaryException,
            EmployeeNameNotValidException {
        return new ResponseEntity<>(employeeService.updateEmployee(id, employeeDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.OK);
    }
}
