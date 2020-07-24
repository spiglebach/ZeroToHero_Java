package hu.zerotohero.verseny.crud.controller;

import hu.zerotohero.verseny.crud.dto.EquipmentDTO;
import hu.zerotohero.verseny.crud.exception.EmptyAttributeException;
import hu.zerotohero.verseny.crud.exception.EquipmentStillOperatedAtLocationException;
import hu.zerotohero.verseny.crud.exception.NoSuchEntityException;
import hu.zerotohero.verseny.crud.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping(value = "")
    public ResponseEntity<?> getEquipments() {
        return new ResponseEntity<>(equipmentService.getEquipments(), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> newEquipment(EquipmentDTO equipmentDTO) throws EmptyAttributeException,
            NoSuchEntityException {
        return new ResponseEntity<>(equipmentService.newEquipment(equipmentDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateEquipment(@PathVariable Long id,
                                             @RequestBody EquipmentDTO equipmentDTO)
                                                throws EmptyAttributeException,
                                                        NoSuchEntityException,
                                                        EquipmentStillOperatedAtLocationException {
        return new ResponseEntity<>(equipmentService.updateEquipment(id, equipmentDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteEquipment(@PathVariable Long id)
                                                throws NoSuchEntityException,
                                                        EquipmentStillOperatedAtLocationException{
        return new ResponseEntity<>(equipmentService.deleteEquipment(id), HttpStatus.OK);
    }
}
