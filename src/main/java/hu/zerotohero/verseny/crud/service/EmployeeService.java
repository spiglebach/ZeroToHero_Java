package hu.zerotohero.verseny.crud.service;

import hu.zerotohero.verseny.crud.entity.Equipment;
import hu.zerotohero.verseny.crud.exception.*;
import hu.zerotohero.verseny.crud.util.EmployeeJob;
import hu.zerotohero.verseny.crud.dto.EmployeeDTO;
import hu.zerotohero.verseny.crud.entity.Employee;
import hu.zerotohero.verseny.crud.entity.Location;
import hu.zerotohero.verseny.crud.repository.EmployeeRepository;
import hu.zerotohero.verseny.crud.util.JobTypePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LocationService locationService;
    @Autowired
    private EquipmentService equipmentService;

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    public Employee newEmployee(EmployeeDTO employeeDTO)
            throws EmptyAttributeException,
                NoSuchEntityException,
                ManagerDoesNotUseEquipmentException,
                IncompatibleJobAndEquipmentException,
                EquipmentAtDifferentLocationException,
                EquipmentAlreadyOperatedException,
                ManagerAlreadyAtLocationException {
        employeeDTO.validate();
        // check constraints
        String name = employeeDTO.getName();
        EmployeeJob job = employeeDTO.getJob();
        Location location = locationService.findById(employeeDTO.getWorksat());
        if (location == null) {
            throw new NoSuchEntityException(Location.class);
        }

        Equipment equipment = null;
        if (employeeDTO.getOperates() != null) {
            equipment = equipmentService.findById(employeeDTO.getOperates());
        }

        validateJobAndEquipment(job, equipment, location);

        Employee newEmployee = new Employee(name, job, location, equipment);
        return employeeRepository.save(newEmployee);
    }

    public Employee updateEmployee(Long employeeId, EmployeeDTO employeeDTO)
            throws EmptyAttributeException, ManagerAlreadyAtLocationException,
                ManagerDoesNotUseEquipmentException, NoSuchEntityException,
                EquipmentAtDifferentLocationException,
                IncompatibleJobAndEquipmentException,
                EquipmentAlreadyOperatedException {
        employeeDTO.validate();
        Employee employee = findById(employeeId);
        if (employee == null) {
            throw new NoSuchEntityException(Employee.class);
        }
        String newName = employeeDTO.getName();
        EmployeeJob newJob = employeeDTO.getJob();
        Location newLocation = locationService.findById(employeeDTO.getWorksat());
        if (newLocation == null) {
            throw new NoSuchEntityException(Location.class);
        }
        Equipment newEquipment = null;
        if (employeeDTO.getOperates() != null) {
            newEquipment = equipmentService.findById(employeeDTO.getOperates());
            if (newEquipment == null) {
                throw new NoSuchEntityException(Equipment.class);
            }
        }


        validateJobAndEquipment(newJob, newEquipment, newLocation);

        employee.setName(newName);
        employee.setJob(newJob);
        employee.setWorksAt(newLocation);
        employee.setOperates(newEquipment);
        return employeeRepository.save(employee);
    }

    public Employee findByOperatedEquipment(Long equipmentId) {
        return employeeRepository.findByOperatedEquipment(equipmentId).orElse(null);
    }

    public List<Employee> getEmployeesByLocation(Long locationId) {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAllByLocation(locationId).forEach((employees::add));
        return employees;
    }

    public Long deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
        return employeeId;
    }

    private List<Employee> getEmployeesByLocationAndJob(Long locationId, EmployeeJob job) {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAllByLocationAndJob(locationId, job).forEach(employees::add);
        return employees;
    }

    private Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    private void validateJobAndEquipment(EmployeeJob job, Equipment equipment, Location location)
                throws ManagerAlreadyAtLocationException, ManagerDoesNotUseEquipmentException,
                        NoSuchEntityException, EquipmentAtDifferentLocationException,
                        IncompatibleJobAndEquipmentException, EquipmentAlreadyOperatedException {
        if (EmployeeJob.MANAGER.equals(job)) {
            if (getEmployeesByLocationAndJob(location.getId(), job).size() > 0) {
                throw new ManagerAlreadyAtLocationException();
            }
            if (equipment != null) {
                throw new ManagerDoesNotUseEquipmentException();
            }
        } else {
            if (equipment == null) {
                throw new NoSuchEntityException(Equipment.class);
            } else if (!equipment.getLocatedAt().equals(location)) {
                throw new EquipmentAtDifferentLocationException();
            }
            if (!equipment.getType().equals(JobTypePair.typeByJob(job))) {
                throw new IncompatibleJobAndEquipmentException();
            }
            Employee employeeOperatingGivenEquipment = findByOperatedEquipment(equipment.getId());
            if (employeeOperatingGivenEquipment != null) {
                throw new EquipmentAlreadyOperatedException();
            }
        }
    }
}
