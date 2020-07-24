package hu.zerotohero.verseny.crud.controller;

import hu.zerotohero.verseny.crud.dto.LocationDTO;
import hu.zerotohero.verseny.crud.exception.EmptyAttributeException;
import hu.zerotohero.verseny.crud.exception.LocationNotEmptyException;
import hu.zerotohero.verseny.crud.exception.NoSuchEntityException;
import hu.zerotohero.verseny.crud.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping(value = "")
    public ResponseEntity<?> getLocations() {
        return new ResponseEntity<>(locationService.getLocations(), HttpStatus.CREATED);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> newLocation(LocationDTO locationDTO) throws EmptyAttributeException {
        return new ResponseEntity<>(locationService.newLocation(locationDTO), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateLocation(@PathVariable Long id,
                                            @Validated @RequestBody LocationDTO locationDTO)
                                                throws EmptyAttributeException, NoSuchEntityException {
        return new ResponseEntity<>(locationService.updateLocation(id, locationDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long id) throws NoSuchEntityException,
                                                                            LocationNotEmptyException {
        return new ResponseEntity<>(locationService.deleteLocation(id), HttpStatus.OK);
    }
}
