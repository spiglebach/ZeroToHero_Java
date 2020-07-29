package hu.zerotohero.verseny;

import hu.zerotohero.verseny.crud.entity.Employee;
import hu.zerotohero.verseny.crud.entity.Equipment;
import hu.zerotohero.verseny.crud.exception.EquipmentAtDifferentLocationException;
import hu.zerotohero.verseny.crud.exception.InsufficientManagerSalaryException;
import hu.zerotohero.verseny.crud.dto.EmployeeDTO;
import hu.zerotohero.verseny.crud.entity.Location;
import hu.zerotohero.verseny.crud.exception.InsufficientSalaryException;
import hu.zerotohero.verseny.crud.exception.SalaryDifferenceException;
import hu.zerotohero.verseny.crud.repository.EmployeeRepository;
import hu.zerotohero.verseny.crud.service.EmployeeService;
import hu.zerotohero.verseny.crud.service.EquipmentService;
import hu.zerotohero.verseny.crud.service.LocationService;
import hu.zerotohero.verseny.crud.util.EmployeeJob;
import hu.zerotohero.verseny.crud.util.EquipmentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeServiceTests {

    private EmployeeService employeeService;

    @Mock
    private LocationService locationService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EquipmentService equipmentService;

    private Location location1;
    private Location location2;
    private Equipment equipment;
    private EmployeeDTO newManagerDTO;
    private EmployeeDTO newCookDTO;
    private Employee manager;
    private Employee cook;

    @Before
    public void setup() {
        employeeService = new EmployeeService(employeeRepository, locationService, equipmentService);
        location1 = new Location("Mordor", "1111 Mount Doom 4.");
        location1.setId(1L);
        location2 = new Location("The Shire", "4444 Hobbiton Took st. 10.");
        location2.setId(2L);
        equipment = new Equipment("Cook's delight", EquipmentType.OVEN, location1);
        equipment.setId(11L);

        newManagerDTO = new EmployeeDTO(
                "Jane Doe",
                EmployeeJob.MANAGER,
                location1.getId(),
                null,
                400);
        newCookDTO = new EmployeeDTO(
                "Chris Cook",
                EmployeeJob.COOK,
                location1.getId(),
                equipment.getId(),
                299);
        manager = new Employee();
        manager.setId(101L);
        cook = new Employee();
        cook.setId(102L);
    }

    @Test(expected = EquipmentAtDifferentLocationException.class)
    public void newEmployee_withEquipmentAtDifferentLocation_expectException() throws Exception {
        when(locationService.findById(any())).thenReturn(location2);
        when(equipmentService.findById(any())).thenReturn(equipment);
        newCookDTO.setWorksat(location2.getId());
        newCookDTO.setSalary(305);
        employeeService.newEmployee(newCookDTO);
    }

    @Test(expected = SalaryDifferenceException.class)
    public void newEmployee_withSalaryDifferenceGreaterThan20PercentOfAvg_expectException() throws Exception {
        when(locationService.findById(any())).thenReturn(location1);
        when(equipmentService.findById(any())).thenReturn(equipment);
        when(employeeRepository.findAvgSalaryByLocationAndJob(any(), any())).thenReturn(Optional.of(1000d));
        newCookDTO.setWorksat(location1.getId());
        newCookDTO.setSalary(1201);
        employeeService.newEmployee(newCookDTO);
    }

    @Test
    public void newEmployee_withSalaryDifferenceExactly20PercentOfAvg_assertEquals() throws Exception {
        when(locationService.findById(any())).thenReturn(location1);
        when(equipmentService.findById(any())).thenReturn(equipment);
        when(employeeRepository.findAvgSalaryByLocationAndJob(any(), any())).thenReturn(Optional.of(1000d));
        when(employeeRepository.save(any())).thenReturn(cook);
        newCookDTO.setWorksat(location1.getId());
        newCookDTO.setSalary(1200);
        assertEquals(Long.valueOf(102L), employeeService.newEmployee(newCookDTO).getId());
    }

    @Test(expected = InsufficientManagerSalaryException.class)
    public void newEmployee_managerWithLowerSalaryThanMaxNonManagerSalary_expectException() throws Exception {
        when(locationService.findById(any())).thenReturn(location1);
        when(employeeRepository.findMaxSalaryOfNonManagersByLocation(any())).thenReturn(Optional.of(450));
        newManagerDTO.setSalary(400);
        employeeService.newEmployee(newManagerDTO);
    }

    @Test
    public void newEmployee_managerWithExactlyMaxNonManagerSalary_assertEquals() throws Exception {
        when(locationService.findById(any())).thenReturn(location1);
        when(employeeRepository.findMaxSalaryOfNonManagersByLocation(any())).thenReturn(Optional.of(450));
        when(employeeRepository.save(any())).thenReturn(manager);
        newManagerDTO.setSalary(450);
        employeeService.newEmployee(newManagerDTO);
        assertEquals(Long.valueOf(101L), manager.getId());
    }

    @Test(expected = InsufficientSalaryException.class)
    public void newEmployee_withLowerSalaryThanMinimalWage_expectException() throws Exception {
        when(locationService.findById(any())).thenReturn(location1);
        when(equipmentService.findById(any())).thenReturn(equipment);
        newCookDTO.setWorksat(location1.getId());
        newCookDTO.setSalary(299);
        employeeService.newEmployee(newCookDTO);
    }
}
