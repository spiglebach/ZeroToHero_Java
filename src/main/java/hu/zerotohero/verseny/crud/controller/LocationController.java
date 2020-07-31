package hu.zerotohero.verseny.crud.controller;

import hu.zerotohero.verseny.crud.dto.LocationDTO;
import hu.zerotohero.verseny.crud.exception.*;
import hu.zerotohero.verseny.crud.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newLocation(@RequestBody LocationDTO locationDTO)
            throws EmptyAttributeException,
                    LocationNameNotValidException,
                    LocationAddressNotValidException {
        return new ResponseEntity<>(locationService.newLocation(locationDTO), HttpStatus.OK);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateLocation(@PathVariable Long id,
                                            @Validated @RequestBody LocationDTO locationDTO)
                                                throws EmptyAttributeException,
                                                        NoSuchEntityException,
                                                        LocationNameNotValidException,
                                                        LocationAddressNotValidException {
        return new ResponseEntity<>(locationService.updateLocation(id, locationDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long id) throws NoSuchEntityException,
                                                                            LocationNotEmptyException {
        return new ResponseEntity<>(locationService.deleteLocation(id), HttpStatus.OK);
    }
}
