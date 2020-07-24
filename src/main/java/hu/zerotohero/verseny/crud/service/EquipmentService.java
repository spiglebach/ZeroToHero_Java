package hu.zerotohero.verseny.crud.service;

import hu.zerotohero.verseny.crud.exception.NoSuchEntityException;
import hu.zerotohero.verseny.crud.util.EquipmentType;
import hu.zerotohero.verseny.crud.dto.EquipmentDTO;
import hu.zerotohero.verseny.crud.entity.Employee;
import hu.zerotohero.verseny.crud.entity.Equipment;
import hu.zerotohero.verseny.crud.entity.Location;
import hu.zerotohero.verseny.crud.exception.EmptyAttributeException;
import hu.zerotohero.verseny.crud.exception.EquipmentStillOperatedAtLocationException;
import hu.zerotohero.verseny.crud.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private LocationService locationService;
    @Autowired
    private EmployeeService employeeService;

    public List<Equipment> getEquipments() {
        ArrayList<Equipment> equipments = new ArrayList<>();
        for (Equipment equipment : equipmentRepository.findAll()) {
            equipments.add(equipment);
        }
        return equipments;
    }

    public Equipment newEquipment(EquipmentDTO equipmentDTO) throws EmptyAttributeException, NoSuchEntityException {
        equipmentDTO.validate();
        Location location = locationService.findById(equipmentDTO.getLocatedat());
        if (location == null) {
            throw new NoSuchEntityException(Location.class);
        }
        Equipment equipment = new Equipment(equipmentDTO.getName(), equipmentDTO.getType(), location);
        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(Long id, EquipmentDTO equipmentDTO) throws EmptyAttributeException,
                                                        NoSuchEntityException,
                                                        EquipmentStillOperatedAtLocationException {
        equipmentDTO.validate();
        Equipment equipment = findById(id);
        if (equipment == null) {
            throw new NoSuchEntityException(Equipment.class);
        }
        Location newLocation = locationService.findById(equipmentDTO.getLocatedat());
        if (newLocation == null) {
            throw new NoSuchEntityException(Location.class);
        }
        if (!equipment.getLocatedAt().equals(newLocation) || !equipment.getType().equals(equipmentDTO.getType())) {
            // Equipment relocation or type change
            Employee employee = employeeService.findByOperatedEquipment(equipment.getId());
            if (employee != null) {
                throw new EquipmentStillOperatedAtLocationException();
            }
        }
        equipment.setName(equipmentDTO.getName());
        equipment.setType(equipmentDTO.getType());
        equipment.setLocatedAt(newLocation);
        return equipmentRepository.save(equipment);
    }

    public Long deleteEquipment(Long id) throws NoSuchEntityException, EquipmentStillOperatedAtLocationException {
        Equipment equipment = findById(id);
        if (equipment == null) {
            throw new NoSuchEntityException(Equipment.class);
        }
        Employee employee = employeeService.findByOperatedEquipment(equipment.getId());
        if (employee != null) {
            throw new EquipmentStillOperatedAtLocationException();
        }
        equipmentRepository.deleteById(id);
        return id;
    }

    public List<Equipment> getEquipmentsByLocation(Long locationId) {
        List<Equipment> equipments = new ArrayList<>();
        equipmentRepository.findAllByLocation(locationId).forEach(equipments::add);
        return equipments;
    }

    public List<Equipment> getEquipmentsByLocationAndType(Long locationId, EquipmentType equipmentType) {
        List<Equipment> equipments = new ArrayList<>();
        equipmentRepository.findAllByLocationAndType(locationId, equipmentType).forEach(equipments::add);
        return equipments;
    }

    public Equipment findById(Long id) {
        return equipmentRepository.findById(id).orElse(null);
    }
}
